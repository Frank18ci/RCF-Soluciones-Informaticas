import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import OrderStatus from '../../model/OrderStatus';
import OrderStatusRequest from '../../model/Orders-Services/OrderStatusRequest.model';

@Injectable({
  providedIn: 'root'
})
export class OrderStatusService {
  private apiUrl = 'http://localhost:8080/orders'; 
  private domain = 'order-status';
  constructor(private http: HttpClient) {
  }  
  getOrderStatuses() : Observable<OrderStatus[]> {
    return this.http.get<OrderStatus[]>(`${this.apiUrl}/${this.domain}`);
  }
  getOrderStatusById(id: number) : Observable<OrderStatus> {
    return this.http.get<OrderStatus>(`${this.apiUrl}/${this.domain}/${id}`);
  }
  saveOrderStatus(orderStatus: OrderStatusRequest) : Observable<OrderStatus> {
    return this.http.post<OrderStatus>(`${this.apiUrl}/${this.domain}`, orderStatus);
  }
  updateOrderStatus(orderId: number, orderStatus: OrderStatusRequest) : Observable<OrderStatus> {
    return this.http.put<OrderStatus>(`${this.apiUrl}/${this.domain}/${orderId}`, orderStatus);
  }
  deleteOrderStatus(orderId: number) : Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${this.domain}/${orderId}`);
  }
}
