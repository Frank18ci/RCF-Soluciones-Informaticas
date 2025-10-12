import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import ProductAttributeValue from '../../ProductAttributeValue';
import ProductAttributeValueRequest from '../../model/Products-Service/ProductAttributeValueRequest.model';

@Injectable({
  providedIn: 'root'
})
export class ProductAttributeValueService {
  private apiUrl = 'http://localhost:8080/products'; 
  private domain = 'product-attribute-values';
  constructor(private http: HttpClient) {
  }
  getProductAttributeValues(): Observable<ProductAttributeValue[]> {
    return this.http.get<ProductAttributeValue[]>(`${this.apiUrl}/${this.domain}`);
  }
  getProductAttributeValueById(id: number): Observable<ProductAttributeValue> {
    return this.http.get<ProductAttributeValue>(`${this.apiUrl}/${this.domain}/${id}`);
  }
  createProductAttributeValue(productAttributeValue: ProductAttributeValueRequest): Observable<ProductAttributeValue> {
    return this.http.post<ProductAttributeValue>(`${this.apiUrl}/${this.domain}`, productAttributeValue);
  }
  updateProductAttributeValue(id: number, productAttributeValue: ProductAttributeValueRequest): Observable<ProductAttributeValue> {
    return this.http.put<ProductAttributeValue>(`${this.apiUrl}/${this.domain}/${id}`, productAttributeValue);
  }
  deleteProductAttributeValue(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${this.domain}/${id}`);
  }
  getProductAttributeValuesByProductId(productId: number): Observable<ProductAttributeValue[]> {
    return this.http.get<ProductAttributeValue[]>(`${this.apiUrl}/${this.domain}/product/${productId}`);
  }
}
