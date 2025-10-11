import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDrawer, MatSidenavModule } from '@angular/material/sidenav';
import { CarritoService } from '../../services/carrito-service';
import { ProductService } from '../../../shared/services/product-services/product-service';
import Product from '../../../shared/model/Product';

@Component({
  selector: 'app-carrito',
  imports: [MatSidenavModule,
    MatButtonModule, CommonModule
  ],
  templateUrl: './carrito.html',
  styleUrl: './carrito.css'
})
export class Carrito implements OnInit, AfterViewInit {
  @ViewChild(MatDrawer) drawer!: MatDrawer;
  constructor(
    private carritoService: CarritoService,
    private productService: ProductService
  ) {

  }
  ngAfterViewInit() {
    if (this.drawer) {
      this.drawer.openedStart.subscribe(() => {
        this.cargarCart();
      });
    } else {
      console.warn('MatDrawer is not available. Please ensure <mat-drawer> exists in the template.');
    }
  }
  products: Product[] = [];
  carritoRecived: any[] = [];
  ngOnInit(): void {
    this.cargarCart();
  }
  cargarCart(){
    this.carritoRecived = this.carritoService.getCart();
    console.log(this.carritoRecived);
    this.carritoRecived.forEach(item => {
      this.productService.getProductById(item.id).subscribe(product => {
        if (product) {
          this.products.push(product);
        }
      });
    });
  }
  getProductQuantity(productId: number): number {
    const item = this.carritoRecived.find(i => i.id === productId);
    return item ? item.quantity : 0;
  }

  cartItems = [
    { name: 'Producto 1', quantity: 2 },
    { name: 'Producto 2', quantity: 1 }
  ];

  openDrawer() {
    this.drawer.open();
  }

  closeDrawer() {
    this.drawer.close();
  }
}
