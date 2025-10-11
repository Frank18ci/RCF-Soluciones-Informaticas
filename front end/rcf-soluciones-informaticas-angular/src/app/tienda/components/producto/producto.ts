import { Component, Input, input, InputSignal } from '@angular/core';
import Product from '../../../shared/model/Product';
import { RootImage } from '../../../shared/services/image-services/RootImage';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CarritoService } from '../../services/carrito-service';
@Component({
  selector: 'app-producto',
  imports: [CommonModule],
  templateUrl: './producto.html',
  styleUrl: './producto.css'
})
export class Producto {
  @Input() product!: Product;
  rootImage = RootImage;
  constructor(
    private router: Router,
    private carritoService: CarritoService
  ){

  }

  verDetalle(productId: number) {
    this.router.navigate(['/tienda/productos', productId]);
  }
  addToCart(productId: number, quantity: number = 1) {
    this.carritoService.addToCart(productId, quantity);
  }
}

