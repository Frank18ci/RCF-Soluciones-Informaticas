import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Service from '../../model/Service';
import ServiceRequest from '../../model/Service-Service/ServiceRequest.model';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  private apiUrl = 'http://localhost:8080/services'; 
  private domain = 'services';
  constructor(private http: HttpClient) {
  }
  getServices() : Observable<Service[]> {
    return this.http.get<Service[]>(`${this.apiUrl}/${this.domain}`);
  }
  getServiceById(id: number) : Observable<Service> {
    return this.http.get<Service>(`${this.apiUrl}/${this.domain}/${id}`);
  }
  saveService(service: ServiceRequest) : Observable<Service> {
    return this.http.post<Service>(`${this.apiUrl}/${this.domain}`, service);
  }
  updateService(id: number, service: ServiceRequest) : Observable<Service> {
    return this.http.put<Service>(`${this.apiUrl}/${this.domain}/${id}`, service);
  }
  deleteService(id: number) : Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${this.domain}/${id}`);
  }
}
