import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import Discount from '../../../shared/model/Discount';
import { DiscountService } from '../../../shared/services/product-services/discount-service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-descuentos-page',
  imports: [MatTableModule, MatPaginatorModule, CommonModule, MatIconModule],
  templateUrl: './descuentos-page.html',
  styleUrl: './descuentos-page.css'
})
export class DescuentosPage implements OnInit, AfterViewInit{
  displayedColumns: string[] = ['Id', 'Nombre', 'Descripcion', 'Tipo descuento', 'Valor', 'Fecha Inicio', 'Fecha Fin', 'Activo', 'Acciones'];
  dataSource = new MatTableDataSource<Discount>([]);
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  constructor(
    private discountService: DiscountService,
    private dialog: MatDialog
  ) { }
  ngOnInit(): void {
    this.loadDiscounts();
  }
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }
  loadDiscounts() {
    this.discountService.getDiscounts().subscribe(discounts => {
      this.dataSource.data = discounts;
    });
  }
  deleteDiscount(discountId: number) {
    if (confirm('¿Estás seguro de que deseas eliminar este descuento?')) {
      this.discountService.deleteDiscount(discountId).subscribe(() => {
        this.loadDiscounts();
      });
    }
  }
  openDialog(discount?: Discount): void {
    console.log('Open dialog for discount:', discount);
  }
  verDiscount(discount: Discount): void {
    console.log('View discount:', discount);
  }
}
