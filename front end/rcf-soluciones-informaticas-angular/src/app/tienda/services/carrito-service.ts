import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  private static readonly cartKey = 'cartRCF-Soluciones-Informaticas';

  addToCart(productId: number, quantity: number = 1) {
    const cart = JSON.parse(localStorage.getItem(CarritoService.cartKey) || '[]');
    const item = cart.find((p: any) => p.id === productId);

    if (item) {
      item.quantity += quantity;
    } else {
      cart.push({ id: productId, quantity });
    }

    localStorage.setItem(CarritoService.cartKey, JSON.stringify(cart));
  }

  getCart() {
    return JSON.parse(localStorage.getItem(CarritoService.cartKey) || '[]');
  }  
}
