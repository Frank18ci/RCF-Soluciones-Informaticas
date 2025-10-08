import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Order from '../../model/Order';
import OrderRequest from '../../model/Orders-Services/OrderRequest.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = 'http://localhost:8080/orders'; 
  private domain = 'orders';
  constructor(private http: HttpClient) {
  }
  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiUrl}/${this.domain}`);
  }
  getOrderById(id: number): Observable<Order> {
    return this.http.get<Order>(`${this.apiUrl}/${this.domain}/${id}`);
  }
  createOrder(order: OrderRequest): Observable<Order> {
    return this.http.post<Order>(`${this.apiUrl}/${this.domain}`, order);
  }
  updateOrder(id: number, order: OrderRequest): Observable<Order> {
    return this.http.put<Order>(`${this.apiUrl}/${this.domain}/${id}`, order);
  }
  deleteOrder(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${this.domain}/${id}`);
  }
}
