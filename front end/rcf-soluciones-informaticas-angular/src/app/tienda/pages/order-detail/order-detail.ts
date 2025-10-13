import { ActivatedRoute } from '@angular/router';
import {  Component, OnInit, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import Order from '../../../shared/model/Order';


@Component({
  selector: 'app-order-detail',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatDividerModule, MatButtonModule,MatIconModule],
  templateUrl: './order-detail.html',
  styleUrl: './order-detail.css'
})
export class OrderDetail implements OnInit {
  order: Order;
  constructor(
    public  dialogRef: MatDialogRef<OrderDetail>,
    @Inject(MAT_DIALOG_DATA) public data: Order,
  ){
    this.order = data;
  }
  ngOnInit(): void { 
  }
  // Función para mostrar los montos en formato monetario
  formatCurrency(cents: number, code: string): string {
    return (cents / 100).toLocaleString('en-US', {
      style: 'currency',
      currency: code
    });
  }

}
