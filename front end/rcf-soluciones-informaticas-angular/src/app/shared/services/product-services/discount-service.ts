import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Discount from '../../model/Discount';
import { Observable } from 'rxjs';

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
}
