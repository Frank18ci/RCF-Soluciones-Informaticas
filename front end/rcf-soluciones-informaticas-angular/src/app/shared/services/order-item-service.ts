import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderItem } from '../model/OrderItem';
import OrderItemRequest from '../model/Orders-Services/OrderItemRequest.model';

@Injectable({
  providedIn: 'root'
})
export class OrderItemService {
  private apiUrl = 'http://localhost:8080/orders'; 
  private domain = 'order-items';
  constructor(private http: HttpClient) {
  }
  getOrderItems() : Observable<OrderItem[]> {
    return this.http.get<OrderItem[]>(`${this.apiUrl}/${this.domain}`);
  }
  getOrderItemById(id: number) : Observable<OrderItem> {
    return this.http.get<OrderItem>(`${this.apiUrl}/${this.domain}/${id}`);
  }
  createOrderItem(orderItem: OrderItemRequest) : Observable<OrderItem> {
    return this.http.post<OrderItem>(`${this.apiUrl}/${this.domain}`, orderItem);
  }
  updateOrderItem(id: number, orderItem: OrderItemRequest) : Observable<OrderItem> {
    return this.http.put<OrderItem>(`${this.apiUrl}/${this.domain}/${id}`, orderItem);
  }
  deleteOrderItem(id: number) : Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${this.domain}/${id}`);
  }
}
