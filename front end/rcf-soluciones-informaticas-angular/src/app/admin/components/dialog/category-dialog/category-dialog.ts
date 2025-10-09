import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import CategoryRequest from '../../../../shared/model/Products-Service/CategoryRequest.model';
import Category from '../../../../shared/model/Category';
import { CategoryService } from '../../../../shared/services/product-services/category-service';

@Component({
  selector: 'app-category-dialog',
  imports: [
    CommonModule,
    FormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDatepickerModule,
    MatSelectModule,
    ReactiveFormsModule
  ],
  templateUrl: './category-dialog.html',
  styleUrl: './category-dialog.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CategoryDialog implements OnInit {
  form!: FormGroup;
  categoryRequest: CategoryRequest = {
    name: '',
    slug: '',
    parentId: 0
  }
  categoryId?: number;
  categories: Category[] = [];

  constructor(
    private dialogRef: MatDialogRef<CategoryDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Category,
    private fb: FormBuilder,
    private categoryService: CategoryService,
    private cdRef: ChangeDetectorRef
  ){
    if(data) {
      this.categoryRequest = {
        name: data.name ?? '',
        slug: data.slug ?? '',
        parentId: data.parent?.id ?? 0
      }
      this.categoryId = data.id;
    }
  }
  ngOnInit(): void {
    this.form = this.fb.group({
      name: [this.categoryRequest.name, [Validators.required, Validators.minLength(3)]],
      slug: [this.categoryRequest.slug, [Validators.required, Validators.minLength(3)]],
      parentId: [this.categoryRequest.parentId, [Validators.required]]
    });
    this.categoryService.getCategories().subscribe({
      next: (cats) => {
        this.categories = cats;
        if(this.categoryRequest.parentId != 0){
          this.form.patchValue({ parentId: this.categoryRequest.parentId });
        }
        this.cdRef.markForCheck();
      },
      error: (err) => console.error('Error fetching categories:', err)
    });
  }
  saveCategory() {
    if (this.form.valid) {
      const updateCategory: CategoryRequest = {
        ...this.categoryRequest,
        ...this.form.value
      }
      this.dialogRef.close(updateCategory);
    }
  }
  closeDialog() {
    this.dialogRef.close();
  }

}
