import { Component } from '@angular/core';
import { Topbar } from '../topbar/topbar';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MaterialModule } from '../../../shared/modules/material/material-module';

@Component({
  selector: 'app-header',
  imports: [Topbar, FormsModule, RouterLink, MatIconModule, MaterialModule],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header {
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
