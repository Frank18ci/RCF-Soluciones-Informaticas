import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import Product from '../../../../shared/model/Product';
import { ProductRequest } from '../../../../shared/model/Products-Service/ProductRequest.model';
import { DiscountService } from '../../../../shared/services/product-services/discount-service';
import { CategoryService } from '../../../../shared/services/product-services/category-service';
import Category from '../../../../shared/model/Category';
import Discount from '../../../../shared/model/Discount';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatSelectModule } from '@angular/material/select';
import { ProductService } from '../../../../shared/services/product-services/product-service';

@Component({
  selector: 'app-product-dialog',
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
  templateUrl: './product-dialog.html',
  styleUrl: './product-dialog.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProductDialog implements OnInit{
  form!: FormGroup;
  productRequest: ProductRequest = {
    sku: '',
    name: '',
    shortDescription: '',
    description: '',
    image: null,
    basePriceCents: 0,
    purchasePriceCents: 0,
    salePriceCents: 0,
    taxRate: 0,
    stock: 0,
    categoryId: 0,
    discountId: 0,
    active: true
  };
  productId?: number;
  nombreImagen: string = '';
  categories: Category[] = [];
  discounts: Discount[] = [];

  constructor(
    private dialogRef: MatDialogRef<ProductDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Product,
    private fb: FormBuilder,
    private discountService: DiscountService,
    private categoryService: CategoryService,
    private productService: ProductService,
    private cdRef: ChangeDetectorRef
    ) {
      if(data){
        this.productRequest = {
          sku: data.sku ?? '',
          name: data.name ?? '',
          shortDescription: data.shortDescription ?? '',
          description: data.description ?? '',
          image: null,
          basePriceCents: data.basePriceCents ?? 0,
          purchasePriceCents: data.purchasePriceCents ?? 0,
          salePriceCents: data.salePriceCents ?? 0,
          taxRate: data.taxRate ?? 0,
          stock: data.stock ?? 0,
          categoryId: data.category?.id ?? 0,
          discountId: data.discount?.id ?? 0,
          active: data.active ?? true
        };
        this.productId = data.id ?? undefined;
        this.nombreImagen = data.imgUrl ?? '';
      }
  }
  ngOnInit() {  
    this.form = this.fb.group({
      sku: [this.productRequest.sku, Validators.required],
      name: [this.productRequest.name, Validators.required],
      shortDescription: [this.productRequest.shortDescription, Validators.required],
      description: [this.productRequest.description, Validators.required],
      basePriceCents: [this.productRequest.basePriceCents, [Validators.required, Validators.min(0)]],
      purchasePriceCents: [this.productRequest.purchasePriceCents, [Validators.required, Validators.min(0)]],
      salePriceCents: [this.productRequest.salePriceCents, [Validators.required, Validators.min(0)]],
      taxRate: [this.productRequest.taxRate, [Validators.required, Validators.min(0)]],
      stock: [this.productRequest.stock, [Validators.required, Validators.min(0)]],
      categoryId: [this.productRequest.categoryId, Validators.required],
      discountId: [this.productRequest.discountId],
      active: [this.productRequest.active, Validators.required]
    });

   this.categoryService.getCategories().subscribe(categories => {
    this.categories = categories;
    if (this.productRequest.categoryId != 0) {
      this.form.patchValue({ categoryId: this.productRequest.categoryId });
    }
    this.cdRef.detectChanges(); 
  });

  this.discountService.getDiscounts().subscribe(discounts => {
    this.discounts = discounts;
    if (this.productRequest.discountId != 0) {
      this.form.patchValue({ discountId: this.productRequest.discountId });
    }
    this.cdRef.detectChanges();
  });
  }
  selectedImageFile: File | null = null;

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    const firstFile: File | null = input.files && input.files.length ? input.files[0] : null;
    this.selectedImageFile = firstFile;
    this.nombreImagen = firstFile ? firstFile.name : '';
  }
  
  saveProduct() {
    if (this.form.invalid) return;

    const formValue = this.form.value;
    const fd = new FormData();

    Object.entries(formValue).forEach(([key, value]) => {
      if (key === 'image' || value === null || value === undefined) return;
      fd.append(key, value.toString());
    });

    if (this.selectedImageFile) {
      fd.append('image', this.selectedImageFile);
    }

    const request$ = this.productId
      ? this.productService.updateProduct(this.productId, fd)
      : this.productService.createProduct(fd);

    request$.subscribe({
      next: res => this.dialogRef.close(res),
      error: err => console.error('Error al guardar producto:', err)
    });
  }
  
  closeDialog() {
    this.dialogRef.close();
  }
}
