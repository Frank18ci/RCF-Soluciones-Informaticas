import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatTable, MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { OrderService } from '../../../shared/services/order-services/order-service';
import Order from '../../../shared/model/Order';
import { OrderDetail } from '../order-detail/order-detail';

@Component({
  selector: 'app-ordenes',
  imports: [MatTableModule, MatPaginatorModule, CommonModule, MatIconModule, MatButtonModule],
  templateUrl: './orders.html',
  styleUrl: './orders.css'
})
export class Orders  implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['id', 'notes', 'fecha', 'total', 'estado', 'acciones'];
  dataSource = new MatTableDataSource<Order>([]);
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  totalOrdenes: number = 0;
  ordenesProcesando: number = 0;
  ordenesFinalizadas: number = 0;
  ordenesCanceladas: number = 0;
  estadoFiltro: string = 'TODAS';

  constructor(private orderService: OrderService, private dialog: MatDialog) {}

  ngOnInit() {
    this.loadOrdenes();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  loadOrdenes() {
    this.orderService.getOrders().subscribe(orders => {
      this.dataSource.data = orders;
      this.totalOrdenes = orders.length;
      this.ordenesProcesando = orders.filter(o => o.orderStatus.code === 'PENDING').length;
      this.ordenesFinalizadas = orders.filter(o => o.orderStatus.code === 'COMPLETED').length;
      this.ordenesCanceladas = orders.filter(o => o.orderStatus.code === 'CANCELLED').length;
    });
  }

  filtrarPorEstado(estado: string) {
    this.estadoFiltro = estado;
    this.orderService.getOrders().subscribe(orders => {
      if (estado === 'TODAS') {
        this.dataSource.data = orders;
      } else {
        this.dataSource.data = orders.filter(o => o.orderStatus.code === estado);
      }
    });
  }

  verDetalles(order: Order) {
    console.log('Ver detalles de la orden:', order);
    this.dialog.open(OrderDetail, {
      width: '600px',
      data: order
    });
  }
}
