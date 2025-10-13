import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { Topbar } from '../topbar/topbar';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MaterialModule } from '../../../shared/modules/material/material-module';
import { BehaviorSubject, fromEvent, map, Observable, startWith, Subscription, throttleTime } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { CarritoService } from '../../services/carrito-service';

@Component({
  selector: 'app-header',
  imports: [Topbar, FormsModule, RouterLink, MatIconModule, MaterialModule, AsyncPipe, MatButtonModule, MatMenuModule],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header implements OnInit, OnDestroy {
   showTopbar!: Observable<boolean>;
   scrollSub!: Subscription;
   storageSub!: Subscription;
  @Output() openCart = new EventEmitter<void>();

  constructor(
    private carritoService: CarritoService
  ) {}
  

  ngOnInit() {
    this.showTopbar = fromEvent(window, 'scroll').pipe(
      throttleTime(50),
      map(() => window.scrollY < 100),
      startWith(true) 
    );

    this.storageSub = fromEvent<StorageEvent>(window, 'storage').subscribe(() => this.updateCartCount());
    this.updateCartCount();
  
  }
  updateCartCount() {
    const cart = this.carritoService.getCart();
    console.log('Cart updated from storage event:', cart);
    this.cartCount = cart?.length || 0;
  }
  ngOnDestroy() {
    this.scrollSub?.unsubscribe();
    this.storageSub?.unsubscribe();
  }

  isMenuOpen = false;
  cartCount = 0;
  q = '';

   onSearch(e: Event) {
    e.preventDefault();
    // TODO: integrar con tu buscador/routeo:
    // this.router.navigate(['/tienda/productos'], { queryParams: { q: this.q } });
    console.log('Buscar:', this.q);
    this.isMenuOpen = false;
  }

  
}
