import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  private static readonly cartKey = 'cartRCF-Soluciones-Informaticas';

  private cartSubject = new BehaviorSubject<any[]>(this.loadFromStorage());
  cart$ = this.cartSubject.asObservable();

  getCart(): any[] {
    return this.cartSubject.getValue();
  }

  addToCart(item: { id: number; quantity?: number }) {
    const cart = [...this.getCart()];
    const existing = cart.find(c => c.id === item.id);
    if (existing) {
      existing.quantity = (existing.quantity || 0) + (item.quantity || 1);
    } else {
      cart.push({ id: item.id, quantity: item.quantity ?? 1 });
    }
    this.saveToStorage(cart);
    this.cartSubject.next(cart);
  }

  removeFromCart(productId: number) {
    const cart = this.getCart().filter(i => i.id !== productId);
    this.saveToStorage(cart);
    this.cartSubject.next(cart);
  }

  private loadFromStorage(): any[] {
    try {
      return JSON.parse(localStorage.getItem('cart') || '[]');
    } catch {
      return [];
    }
  }

  private saveToStorage(cart: any[]) {
    localStorage.setItem('cart', JSON.stringify(cart));
  }
}
