import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import DiscountRequest from '../../../../shared/model/Products-Service/DiscountRequest.model';
import DiscountType from '../../../../shared/model/DiscountType';
import Discount from '../../../../shared/model/Discount';
import { DiscountTypeService } from '../../../../shared/services/product-services/discount-type-service';

@Component({
  selector: 'app-descuento-dialog',
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
  templateUrl: './descuento-dialog.html',
  styleUrl: './descuento-dialog.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DescuentoDialog implements OnInit {
  form!: FormGroup;
  descuentoRequest: DiscountRequest = {
    code: '',
    description: '',
    discountTypeId: 0,
    value: 0,
    active: true,
    startDate: new Date().toISOString().split('T')[0],
    endDate: new Date().toISOString().split('T')[0]
  }
  descuentoId?: number;
  discountTypes : DiscountType[] = [];
  constructor(
    private dialogRef: MatDialogRef<DescuentoDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Discount,
    private fb: FormBuilder,
    private discountTypeService: DiscountTypeService,
    private cdRef: ChangeDetectorRef
  ) { 
    if(data){
      this.descuentoRequest = {
        code: data.code ?? '',
        description: data.description ?? '',
        discountTypeId: data.discountType?.id ?? 0,
        value: data.value ?? 0,
        active: data.active ?? true,
        startDate: data.startDate ? new Date(data.startDate).toISOString().split('T')[0] : new Date().toISOString().split('T')[0],
        endDate: data.endDate ? new Date(data.endDate).toISOString().split('T')[0] : new Date().toISOString().split('T')[0]
      }
      this.descuentoId = data.id;
    }
  }
  ngOnInit(): void {
    this.form = this.fb.group({
      code: [this.descuentoRequest.code, [Validators.required, Validators.minLength(3)]],
      description: [this.descuentoRequest.description, [Validators.required, Validators.minLength(3)]],
      discountTypeId: [this.descuentoRequest.discountTypeId, [Validators.required]],
      value: [this.descuentoRequest.value, [Validators.required, Validators.min(0)]],
      active: [this.descuentoRequest.active, [Validators.required]],
      startDate: [this.descuentoRequest.startDate, [Validators.required]],
      endDate: [this.descuentoRequest.endDate, [Validators.required]]
    });
    this.discountTypeService.getDiscountTypes().subscribe({
      next: (types) => {
        this.discountTypes = types;
        if(this.descuentoRequest.discountTypeId != 0){
          this.form.patchValue({ discountTypeId: this.descuentoRequest.discountTypeId });
        }
        this.cdRef.markForCheck();
      },
      error: (err) => console.error('Error fetching discount types:', err)
    });
  }
  saveDescuento() {
    if (this.form.valid) {
      this.form.value.startDate = new Date(this.form.value.startDate).toISOString();
      this.form.value.endDate = new Date(this.form.value.endDate).toISOString();

      const updateDescuento: DiscountRequest = {
        ...this.descuentoRequest,
        ...this.form.value
      }
      this.dialogRef.close(updateDescuento);
    }
  }
  closeDialog() {
    this.dialogRef.close();
  }
  
}
