import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from '../../components/header/header';
import { Footer } from '../../components/footer/footer';
import { Carrito } from '../../components/carrito/carrito';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-layout-page',
  imports: [RouterOutlet, Header, Footer, Carrito, MatSidenavModule,
    MatButtonModule, CommonModule],
  templateUrl: './layout-page.html',
  styleUrl: './layout-page.css'
})
export class LayoutPage {

}
