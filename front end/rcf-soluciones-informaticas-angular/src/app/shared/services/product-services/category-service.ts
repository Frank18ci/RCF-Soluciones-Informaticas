import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Category from '../../model/Category';
import { Observable } from 'rxjs';
import CategoryRequest from '../../model/Products-Service/CategoryRequest.model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private apiUrl = 'http://localhost:8080/products'; 
  private domain = 'categories';
  constructor(private http: HttpClient) {
  }
  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.apiUrl}/${this.domain}`);
  }
  getCategoryById(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.apiUrl}/${this.domain}/${id}`);
  }
  createCategory(category: CategoryRequest): Observable<Category> {
    return this.http.post<Category>(`${this.apiUrl}/${this.domain}`, category);
  }
  updateCategory(id: number, category: CategoryRequest): Observable<Category> {
    return this.http.put<Category>(`${this.apiUrl}/${this.domain}/${id}`, category);
  }
  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${this.domain}/${id}`);
  }
  getCategoriesByName(name: string): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.apiUrl}/${this.domain}/search?name=${name}`);
  }
}
