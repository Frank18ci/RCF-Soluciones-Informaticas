import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import RolResponse from '../../model/RolResponse';
import RolRequest from '../../model/User-Service/RolRequest.model';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  private apiUrl = 'http://localhost:8080/users'; 
  private domain = 'roles';
  constructor(private http: HttpClient) {
  }
  getRoles() : Observable<RolResponse[]> {
    return this.http.get<RolResponse[]>(`${this.apiUrl}/${this.domain}`);
  }
  getRoleById(id: number) : Observable<RolResponse> {
    return this.http.get<RolResponse>(`${this.apiUrl}/${this.domain}/${id}`);
  }
  createRole(role: RolRequest) : Observable<RolResponse> {
    return this.http.post<RolResponse>(`${this.apiUrl}/${this.domain}`, role);
  }
  updateRole(id: number, role: RolRequest) : Observable<RolResponse> {
    return this.http.put<RolResponse>(`${this.apiUrl}/${this.domain}/${id}`, role);
  }
  deleteRole(id: number) : Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${this.domain}/${id}`);
  }

}
