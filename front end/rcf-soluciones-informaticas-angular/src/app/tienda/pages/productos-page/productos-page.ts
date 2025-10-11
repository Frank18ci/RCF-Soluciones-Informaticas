import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../shared/services/product-services/product-service';
import Product from '../../../shared/model/Product';
import { Producto } from '../../components/producto/producto';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CategoryService } from '../../../shared/services/product-services/category-service';
import Category from '../../../shared/model/Category';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import {MatSliderModule} from '@angular/material/slider';

@Component({
  selector: 'app-productos-page',
  imports: [Producto, CommonModule, ReactiveFormsModule, MatSliderModule],
  templateUrl: './productos-page.html',
  styleUrl: './productos-page.css'
})
export class ProductosPage implements OnInit {
  formSearch! :FormGroup;
  products: Product[] = [];
  categories: Category[] = [];
  constructor(
    private productService: ProductService,
    private router: Router,
    private categoryService: CategoryService,
    private fb: FormBuilder
  ) {
    this.formSearch = this.fb.group({
      name: [''],
      categoryId: [0],
      priceMin: [0],
      priceMax: [10000]
    });
  }
  ngOnInit() {
    this.productService.getProducts().subscribe(products => {
      this.products = products;
    });
    this.categoryService.getCategories().subscribe(categories => {
      this.categories = categories;
    });
  }
  calculateMaxPrice() {
    const prices = this.products.map(p => p.basePriceCents / 100);
    return Math.max(...prices, 10000); 
  }
  search() {
    const { name, categoryId, priceMin, priceMax } = this.formSearch.value;
    
    this.productService.searchProducts(name, categoryId, priceMin, priceMax).subscribe(products => {
      this.products = products;
    });
  }
}
