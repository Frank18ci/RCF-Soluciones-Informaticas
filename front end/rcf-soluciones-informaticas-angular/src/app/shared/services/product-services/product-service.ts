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
  createProduct(product: ProductRequest): Observable<Product> {
    return this.http.post<Product>(`${this.apiUrl}/${this.domain}`, product);
  }
  updateProduct(id: number, product: ProductRequest): Observable<Product> {
    const url = `${this.apiUrl}/${this.domain}/${id}`;
    return this.http.put<Product>(url, product);
  }
  deleteProduct(id: number): Observable<void> {
    const url = `${this.apiUrl}/${this.domain}/${id}`;
    return this.http.delete<void>(url);
  }
}
