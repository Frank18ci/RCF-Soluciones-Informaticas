import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import User from '../../model/User';
import UserRequest from '../../model/User-Service/UserRequest.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/users'; 
  private domain = 'users';
  constructor(private http: HttpClient) {
  }
  getUsers() : Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/${this.domain}`);
  }
  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${this.domain}/${id}`);
  }
  createUser(user: UserRequest): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/${this.domain}`, user);
  }
  updateUser(id: number, user: UserRequest): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${this.domain}/${id}`, user);
  }
  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${this.domain}/${id}`);
  }
}
