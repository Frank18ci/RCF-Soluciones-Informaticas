import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDrawer, MatSidenavModule } from '@angular/material/sidenav';
import { Subscription } from 'rxjs';
import { skip } from 'rxjs/operators';
import { CarritoService } from '../../services/carrito-service';
import { ProductService } from '../../../shared/services/product-services/product-service';
import Product from '../../../shared/model/Product';
import { RootImage } from '../../../shared/services/image-services/RootImage';
import { MatIconModule } from '@angular/material/icon';
import { OrderService } from '../../../shared/services/order-services/order-service';
import { OrderItemService } from '../../../shared/services/order-item-service';
import Order from '../../../shared/model/Order';
import OrderRequest from '../../../shared/model/Orders-Services/OrderRequest.model';
import { OrderItem } from '../../../shared/model/OrderItem';
import OrderItemRequest from '../../../shared/model/Orders-Services/OrderItemRequest.model';

@Component({
  selector: 'app-carrito',
  imports: [MatSidenavModule, MatButtonModule, CommonModule, MatIconModule],
  templateUrl: './carrito.html',
  styleUrl: './carrito.css'
})
export class Carrito implements OnInit, AfterViewInit, OnDestroy {
  @ViewChild(MatDrawer) drawer!: MatDrawer;
  private drawerSub?: Subscription;
  private cartSub?: Subscription;
  rootImage = RootImage;

  constructor(
    private carritoService: CarritoService,
    private productService: ProductService,
    private orderService: OrderService,
    private orderItemService: OrderItemService
  ) {}

  ngAfterViewInit() {
    if (this.drawer) {
      this.drawerSub = this.drawer.openedChange.subscribe(isOpen => {
        if (isOpen) {
          this.loadCartItems();
        }
      });
    }
  }

  // ahora guardamos objetos con producto + cantidad
  cartItems: Array<{ product: Product; quantity: number }> = [];
  carritoRecived: any[] = [];

  ngOnInit(): void {
    this.cartItems = []; // inicializar para evitar errores en la UI
    this.loadCartItems();

    // suscribirse a cambios del carrito para recargar
    // ignoramos la emisión inicial (skip(1)) para no recargar doble al iniciar
    this.cartSub = this.carritoService.cart$.pipe(skip(1)).subscribe(() => {
      this.loadCartItems();
    });
  }

  // carga y agrupa por id => evita duplicados en la UI
  loadCartItems() {
    this.cartItems = [];
    this.carritoRecived = this.carritoService.getCart() || [];

    // Map id -> total quantity
    const qtyMap = new Map<number, number>();
    this.carritoRecived.forEach(i => {
      const id = Number(i.id);
      const q = Number(i.quantity ?? 1);
      qtyMap.set(id, (qtyMap.get(id) ?? 0) + q);
    });

    const ids = Array.from(qtyMap.keys());
    ids.forEach(id => {
      this.productService.getProductById(id).subscribe(product => {
        if (product) {
          this.cartItems.push({ product, quantity: qtyMap.get(id) ?? 0 });
        }
      });
    });
  }

  // trackBy para el *ngFor
  trackByProduct(index: number, item: { product: Product; quantity: number }) {
    return item.product?.id ?? index;
  }

  getProductQuantity(productId: number): number {
    const it = this.cartItems.find(c => c.product.id === productId);
    return it ? it.quantity : 0;
  }

  deleteProduct(productId: number): void {
    this.carritoService.removeFromCart(productId);
    // actualizar UI localmente (la suscripción a cart$ recargará también)
    this.cartItems = this.cartItems.filter(c => c.product.id !== productId);
  }

  incrementQuantity(productId: number): void {
    this.carritoService.addToCart({ id: productId, quantity: 1 });
    const it = this.cartItems.find(c => c.product.id === productId);
    if (it) it.quantity++;
  }

  decrementQuantity(productId: number): void {
    const current = this.getProductQuantity(productId);
    if (current > 1) {
      this.carritoService.addToCart({ id: productId, quantity: -1 });
      const it = this.cartItems.find(c => c.product.id === productId);
      if (it) it.quantity--;
    } else {
      // si llega a 0 quitar
      this.deleteProduct(productId);
    }
  }

  getTotalPrice(): number {
    return this.cartItems.reduce((total, item) => {
      const price = (item.product.salePriceCents ?? 0) / 100;
      return total + price * item.quantity;
    }, 0);
  }

  openDrawer() {
    this.loadCartItems();
    this.drawer.open();
  }

  closeDrawer() {
    this.drawer.close();
  }

  ngOnDestroy(): void {
    this.drawerSub?.unsubscribe();
    this.cartSub?.unsubscribe();
  }
  crearOrden() {
    const order: OrderRequest= {
      currencyCode: 'PEN',
      notes: 'Orden creada desde el carrito',
      userId: 1, // Change when auth is implemented
      orderStatusId: 1, // Pending status, change as needed
      discountCents: 0,
      taxTotalCents: 0,
      totalCents: Math.round(this.getTotalPrice() * 100),
      subtotalCents: Math.round(this.getTotalPrice() * 100),
      paymentMethodCode: 'CARD'
    }
    this.orderService.createOrder(order).subscribe((createdOrder: Order) => {
      console.log('Orden creada:', createdOrder);
      this.cartItems.forEach(ci => {
            const orderItem: OrderItemRequest = {
              productId: ci.product.id,
              qty: ci.quantity,
              serviceId: 0,
              orderId: createdOrder.id,
              discountLineCents: 0,
              taxRate: 0,
              unitPriceCents: ci.product.salePriceCents ?? 0,
              totalLineCents: (ci.product.salePriceCents ?? 0) * ci.quantity
            };
            this.orderItemService.createOrderItem(orderItem).subscribe(() => {
              console.log('Item de orden creado para producto:', ci.product.id);
            });
          });      
      // Limpiar el carrito después de crear la orden
      this.carritoService.clearCart();
      this.closeDrawer();
    });
  }
}
