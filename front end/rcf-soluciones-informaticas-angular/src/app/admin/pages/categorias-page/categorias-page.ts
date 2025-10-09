import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import CategoryRequest from '../../../shared/model/Products-Service/CategoryRequest.model';
import { CategoryService } from '../../../shared/services/product-services/category-service';
import { MatDialog } from '@angular/material/dialog';
import Category from '../../../shared/model/Category';
import { CategoryDialog } from '../../components/dialog/category-dialog/category-dialog';

@Component({
  selector: 'app-categorias-page',
  imports: [MatTableModule, MatPaginatorModule, CommonModule, MatIconModule],
  templateUrl: './categorias-page.html',
  styleUrl: './categorias-page.css'
})
export class CategoriasPage implements OnInit, AfterViewInit{
  displayedColumns: string[] = ['Id', 'Nombre', 'Slug', 'Categoría padre', 'Acciones'];
  dataSource = new MatTableDataSource<Category>([]);
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private categoryService: CategoryService,
    private dialog: MatDialog
  ) { }
  ngOnInit(): void {
    this.loadCategories();
  }
  loadCategories() {
    this.categoryService.getCategories().subscribe(categories => {
      this.dataSource.data = categories;
    });
  }
  openDialog(category?: Category) {
    let categoryDialogData: any = {};
    if (category) {
      categoryDialogData = { ...category };
    }
    const dialogRef = this.dialog.open(CategoryDialog, {
      width: '700px',
      data: category? categoryDialogData : {}
    })
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (categoryDialogData.id) {
          this.categoryService.updateCategory(categoryDialogData.id, result).subscribe({
            next: () => this.loadCategories(),
            error: (err) => console.error('Error al actualizar categoría:', err)
          });
        } else {
          this.categoryService.createCategory(result).subscribe({
            next: () => this.loadCategories(),
            error: (err) => console.error('Error al crear categoría:', err)
          });
        }
      }
    });
  }
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }
  verCategoria(category: Category) {
    this.categoryService.getCategoryById(category.id).subscribe(cat => {
      console.log(cat);
    });
  }
  deleteCategoria(categoryId: number) {
    this.categoryService.deleteCategory(categoryId).subscribe({
      next: () => this.loadCategories(),
      error: (err) => console.error('Error al eliminar categoría:', err)
    });
  }

}
