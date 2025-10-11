import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Product from '../../model/Product';
import { ProductRequest } from '../../model/Products-Service/ProductRequest.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = 'http://localhost:8080/products'; 
  private domain = 'products';
  constructor(private http: HttpClient) {
  }
  getProducts() : Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}/${this.domain}`);
  }
  getProductById(id: number): Observable<Product> {
    const url = `${this.apiUrl}/${this.domain}/${id}`;
    return this.http.get<Product>(url);
  }
  createProduct(product: FormData): Observable<Product> {
    return this.http.post<Product>(`${this.apiUrl}/${this.domain}`, product);
  }
  updateProduct(id: number, product: FormData): Observable<Product> {
    const url = `${this.apiUrl}/${this.domain}/${id}`;
    return this.http.put<Product>(url, product);
  }
  deleteProduct(id: number): Observable<void> {
    const url = `${this.apiUrl}/${this.domain}/${id}`;
    return this.http.delete<void>(url);
  }

  searchProducts(name: String = "", categoryId: number, minPrice: number, maxPrice: number): Observable<Product[]> {
    const params = {} as any;
    params.name = name;
    params.categoryId = categoryId;
    if (minPrice != null) params.minPrice = minPrice * 100;
    if (maxPrice != null) params.maxPrice = maxPrice * 100;
    return this.http.get<Product[]>(`${this.apiUrl}/${this.domain}/search`, { params });
  }
}
