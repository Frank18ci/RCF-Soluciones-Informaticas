import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ProductService } from '../../../shared/services/product-services/product-service';
import { MatTable, MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import Product from '../../../shared/model/Product';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';
import { ProductDialog } from '../../components/dialog/product-dialog/product-dialog';

@Component({
  selector: 'app-productos-page',
  imports: [MatTableModule, MatPaginatorModule, CommonModule, MatIconModule],
  templateUrl: './productos-page.html',
  styleUrl: './productos-page.css'
})
export class ProductosPage implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['SKU', 'Nombre', 'Descripción corta', 'Precio (S/.)', 'Stock', 'Categoría', 'Activo', 'Acciones'];
  dataSource = new MatTableDataSource<Product>([]);
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private productService: ProductService, private dialog: MatDialog) {}

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  ngOnInit() {
    this.loadProductos();
  }
  loadProductos(){
    this.productService.getProducts().subscribe(products => {
      this.dataSource.data = products;
    });
  }

  openDialog(product?: Product) {
    let productDialogData: any = {};
    if(product){
      productDialogData = { ...product }; 
    }
    const dialogRef = this.dialog.open(ProductDialog, {
      width: '700px',
      data: product ? productDialogData : {}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if(productDialogData.id){
          this.productService.updateProduct(productDialogData.id, result).subscribe({
              next: () => {
                this.loadProductos();
              },
              error: (err) => {
                console.error('Error al actualizar producto:', err);
              }
          });
        } else {
          this.productService.createProduct(result).subscribe({
              next: () => {
                  this.loadProductos();
              },
              error: (err) => {
                  console.error('Error al crear producto:', err);
              }
          });
        }
      }
    });
  }
  verProducto(productId: number) {
    this.productService.getProductById(productId).subscribe(product => {
      console.log('Producto:', product);
    });
  }
  deleteProduct(productId: number) {
    this.productService.deleteProduct(productId).subscribe(() => {
      this.loadProductos();
    });
  }
}
