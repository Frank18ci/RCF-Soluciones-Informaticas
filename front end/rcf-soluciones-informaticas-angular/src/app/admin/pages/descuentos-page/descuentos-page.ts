import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import Discount from '../../../shared/model/Discount';
import { DiscountService } from '../../../shared/services/product-services/discount-service';
import { MatDialog } from '@angular/material/dialog';
import { DescuentoDialog } from '../../components/dialog/descuento-dialog/descuento-dialog';

@Component({
  selector: 'app-descuentos-page',
  imports: [MatTableModule, MatPaginatorModule, CommonModule, MatIconModule],
  templateUrl: './descuentos-page.html',
  styleUrl: './descuentos-page.css'
})
export class DescuentosPage implements OnInit, AfterViewInit{
  displayedColumns: string[] = ['Id', 'Code', 'Descripcion', 'Tipo descuento', 'Valor', 'Activo', 'Fecha Inicio', 'Fecha Fin', 'Acciones'];
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
      console.log('Discounts loaded:', discounts);
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
    let discountDialogData: any = {};
    if (discount) {
      discountDialogData = { ...discount };
    }
    const dialogRef = this.dialog.open(DescuentoDialog, {
      width: '700px',
      data: discount ? discountDialogData : {}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (discountDialogData.id) {
          this.discountService.updateDiscount(discountDialogData.id, result).subscribe({
            next: () => this.loadDiscounts(),
            error: (err) => console.error('Error al actualizar descuento:', err)
          });
        } else {
          this.discountService.createDiscount(result).subscribe({
            next: () => this.loadDiscounts(),
            error: (err) => console.error('Error al crear descuento:', err)
          });
        }
      }
    });
  }
  verDiscount(discount: Discount): void {
    console.log('View discount:', discount);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.buscarDescuentoPorCodigo(filterValue.trim().toLowerCase());
  }
  buscarDescuentoPorCodigo(codigo: string) {
    this.discountService.getDiscountsByCode(codigo).subscribe(descuentos => {
      this.dataSource.data = descuentos;
    });
  }
}
