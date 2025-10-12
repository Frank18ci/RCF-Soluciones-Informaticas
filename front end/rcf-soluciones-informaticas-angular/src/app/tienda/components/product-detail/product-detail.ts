import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ProductService } from '../../../shared/services/product-services/product-service';
import Product from '../../../shared/model/Product';
import { AdminRoutingModule } from "../../../admin/admin-routing-module";
import { RootImage } from '../../../shared/services/image-services/RootImage';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { CarritoService } from '../../services/carrito-service';
import { ProductAttributeValueService } from '../../../shared/services/product-services/product-attribute-value-service';
import ProductAttributeValue from '../../../shared/ProductAttributeValue';

@Component({
  selector: 'app-product-detail',
  imports: [AdminRoutingModule, RouterLink, CommonModule, MatIconModule],
  templateUrl: './product-detail.html',
  styleUrl: './product-detail.css'
})
export class ProductDetail implements OnInit {
  product!: Product;
  rootImage = RootImage;
  productAttributeValues: ProductAttributeValue[] = [];
  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private carritoService: CarritoService,
    private productAttributeValueService: ProductAttributeValueService
  ) {}
  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.productService.getProductById(Number(id)).subscribe((product) => {
        this.product = product;
        console.log('Producto cargado:', this.product);
      });
    }
    this.productAttributeValueService.getProductAttributeValuesByProductId(Number(id)).subscribe((values) => {
      this.productAttributeValues = values;
      console.log('Atributos cargados:', this.productAttributeValues);
    });
  }
  cantidad = 1;
  incrementarCantidad() {
    this.cantidad++;
  }
  decrementarCantidad() {
    if (this.cantidad > 1) {
      this.cantidad--;
    }
  }
  addToCart() {
    this.carritoService.addToCart({id: this.product.id, quantity: this.cantidad});
  }
}
