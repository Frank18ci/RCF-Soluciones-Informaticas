import { Component, OnDestroy, OnInit } from '@angular/core';
import { Topbar } from '../topbar/topbar';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MaterialModule } from '../../../shared/modules/material/material-module';
import { BehaviorSubject, fromEvent, map, Observable, startWith, Subscription, throttleTime } from 'rxjs';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-header',
  imports: [Topbar, FormsModule, RouterLink, MatIconModule, MaterialModule, AsyncPipe],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header implements OnInit, OnDestroy {
   showTopbar: Observable<boolean> = new BehaviorSubject(true);


  ngOnInit() {
    this.showTopbar = fromEvent(window, 'scroll').pipe(
      throttleTime(50),
      map(() => window.scrollY > 100),
      startWith(window.scrollY > 100)
    );
    
  }
  ngOnDestroy() {
    
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

  toggleCart() {
    // TODO: abre mini-carrito/drawer
    console.log('Abrir carrito');
  }
}
