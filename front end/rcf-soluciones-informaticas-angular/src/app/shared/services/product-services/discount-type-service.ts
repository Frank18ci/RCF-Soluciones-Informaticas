import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import DiscountType from '../../model/DiscountType';
import DiscountTypeRequest from '../../model/Products-Service/DiscountTypeRequest.model';

@Injectable({
  providedIn: 'root'
})
export class DiscountTypeService {
  private apiUrl = 'http://localhost:8080/products'; 
  private domain = 'discount-types';

  constructor(private http: HttpClient) {
  }

  getDiscountTypes() : Observable<DiscountType[]> {
    return this.http.get<DiscountType[]>(`${this.apiUrl}/${this.domain}`);
  }
  getDiscountTypeById(id: number) : Observable<DiscountType> {
    return this.http.get<DiscountType>(`${this.apiUrl}/${this.domain}/${id}`);
  }
  createDiscountType(discountType: DiscountTypeRequest) : Observable<DiscountType> {
    return this.http.post<DiscountType>(`${this.apiUrl}/${this.domain}`, discountType);
  }
  updateDiscountType(id: number, discountType: DiscountTypeRequest) : Observable<DiscountType> {
    return this.http.put<DiscountType>(`${this.apiUrl}/${this.domain}/${id}`, discountType);
  }
  deleteDiscountType(id: number) : Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${this.domain}/${id}`);
  }


}
