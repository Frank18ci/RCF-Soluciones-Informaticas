import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Discount from '../../model/Discount';
import { Observable } from 'rxjs';
import DiscountRequest from '../../model/Products-Service/DiscountRequest.model';

@Injectable({
  providedIn: 'root'
})
export class DiscountService {
  private apiUrl = 'http://localhost:8080/products'; 
  private domain = 'discounts';
  constructor(private http: HttpClient) {
  }
  getDiscounts(): Observable<Discount[]> {
    return this.http.get<Discount[]>(`${this.apiUrl}/${this.domain}`);
  }
  getDiscountById(id: number): Observable<Discount> {
    return this.http.get<Discount>(`${this.apiUrl}/${this.domain}/${id}`);
  }
  createDiscount(discount: DiscountRequest): Observable<Discount> {
    return this.http.post<Discount>(`${this.apiUrl}/${this.domain}`, discount);
  }
  updateDiscount(id: number, discount: DiscountRequest): Observable<Discount> {
    return this.http.put<Discount>(`${this.apiUrl}/${this.domain}/${id}`, discount);
  }
  deleteDiscount(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${this.domain}/${id}`);
  }
  getDiscountsByCode(code: string): Observable<Discount[]> {
    const params = { code: code };
    return this.http.get<Discount[]>(`${this.apiUrl}/${this.domain}/search`, { params });
  }
}
