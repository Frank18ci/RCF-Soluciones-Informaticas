import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import Order from '../../../shared/model/Order';
import { OrderService } from '../../../shared/services/order-services/order-service';
import { MatDialog } from '@angular/material/dialog';
import { OrderDialog } from '../../components/dialog/order-dialog/order-dialog';

@Component({
  selector: 'app-ordenes-page',
  imports: [MatTableModule, MatPaginatorModule, CommonModule, MatIconModule],
  templateUrl: './ordenes-page.html',
  styleUrl: './ordenes-page.css'
})
export class OrdenesPage implements OnInit, AfterViewInit{
  displayedColumns: string[] = ['Codigo de Orden', 'Estado', 'Metodo de Pago', 'Subtotal (S/.)', 'Impuestos (S/.)', 'Total (S/.)', 'Descuento (S/.)', 'Acciones'];
  dataSource = new MatTableDataSource<Order>([]);
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private orderService: OrderService, private dialog: MatDialog) {}

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  ngOnInit() {
    this.loadOrdenes();
  }
  loadOrdenes(){
    this.orderService.getOrders().subscribe(orders => {
      this.dataSource.data = orders;
    });
  }
  openDialog(order?: Order) {
    let orderDialogData: any = {};
    if (order) {
      orderDialogData = { ...order };
    }
    const dialogRef = this.dialog.open(OrderDialog, {
      width: '700px',
      data: order? orderDialogData : {}
    })
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (orderDialogData.id) {
          this.orderService.updateOrder(orderDialogData.id, result).subscribe({
            next: () => this.loadOrdenes(),
            error: (err) => console.error('Error al actualizar orden:', err)
          });
        } else {
          this.orderService.createOrder(result).subscribe({
            next: () => this.loadOrdenes(),
            error: (err) => console.error('Error al crear orden:', err)
          });
        }
      }
    });
  }
  verOrder(orderId: number) {
    this.orderService.getOrderById(orderId).subscribe(order => {
      console.log('Orden:', order);
    });
  }
  deleteOrder(orderId: number) {
    if (confirm('¿Estás seguro de que deseas eliminar esta orden?')) {
      this.orderService.deleteOrder(orderId).subscribe(() => {
        this.loadOrdenes();
      });
    }
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.buscarOrdenesPorStatusCode(filterValue.trim().toLowerCase());
  }
  buscarOrdenesPorStatusCode(code: string) {
    this.orderService.getOrdersByOrderStatusCode(code).subscribe(orders => {
      this.dataSource.data = orders;
    });
  }
}

