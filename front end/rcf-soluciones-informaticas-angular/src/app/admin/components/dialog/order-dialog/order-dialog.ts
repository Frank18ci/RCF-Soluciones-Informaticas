import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import OrderRequest from '../../../../shared/model/Orders-Services/OrderRequest.model';
import User from '../../../../shared/model/User';
import OrderStatus from '../../../../shared/model/OrderStatus';
import Order from '../../../../shared/model/Order';
import { UserService } from '../../../../shared/services/user-services/user-service';
import { OrderStatusService } from '../../../../shared/services/order-services/order-status-service';

@Component({
  selector: 'app-order-dialog',
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
  templateUrl: './order-dialog.html',
  styleUrl: './order-dialog.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class OrderDialog implements OnInit {
  form!: FormGroup;
  orderRequest: OrderRequest = {
    userId: 0,
    currencyCode: '',
    orderStatusId: 0,
    paymentMethodCode: '',
    subtotalCents: 0,
    taxTotalCents: 0,
    totalCents: 0,
    discountCents: 0,
    notes: ''
  }
  orderId?: number;
  users: User[] = [];
  orderStatuses: OrderStatus[] = [];

  constructor(
    private dialogRef: MatDialogRef<OrderDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Order,
    private fb: FormBuilder,
    private userService: UserService,
    private orderStatusService: OrderStatusService,
    private cdRef: ChangeDetectorRef
  ) { 
    if(data){
      this.orderRequest = {
        userId: data.userId ?? 0,
        currencyCode: data.currencyCode ?? '',
        orderStatusId: data.orderStatus?.id ?? 0,
        paymentMethodCode: data.paymentMethodCode ?? '',
        subtotalCents: data.subtotalCents ?? 0,
        taxTotalCents: data.taxTotalCents ?? 0,
        totalCents: data.totalCents ?? 0,
        discountCents: data.discountCents ?? 0,
        notes: data.notes ?? ''
      }
      this.orderId = data.id;
    }
  }
  ngOnInit() {
    this.form = this.fb.group({
      userId: [this.orderRequest.userId, Validators.required],
      currencyCode: [this.orderRequest.currencyCode, Validators.required],
      orderStatusId: [this.orderRequest.orderStatusId, Validators.required],
      paymentMethodCode: [this.orderRequest.paymentMethodCode, Validators.required],
      subtotalCents: [this.orderRequest.subtotalCents, Validators.required],
      taxTotalCents: [this.orderRequest.taxTotalCents, Validators.required],
      totalCents: [this.orderRequest.totalCents, Validators.required],
      discountCents: [this.orderRequest.discountCents, Validators.required],
      notes: [this.orderRequest.notes, Validators.required]
    });
    this.userService.getUsers().subscribe(users => {
      this.users = users;
      if (this.orderRequest.userId != 0) {
        this.form.patchValue({ userId: this.orderRequest.userId });
      }
      this.cdRef.detectChanges(); 
    });
    this.orderStatusService.getOrderStatuses().subscribe(statuses => {
      this.orderStatuses = statuses;
      if (this.orderRequest.orderStatusId != 0) {
        this.form.patchValue({ orderStatusId: this.orderRequest.orderStatusId });
      }
      this.cdRef.detectChanges();
    });
  }

  saveOrder() {
    if (this.form.valid) {
      const updateOrder: OrderRequest = {
        ...this.orderRequest,
        ...this.form.value
      };
      this.dialogRef.close(updateOrder);
    }
  }
  closeDialog() {
    this.dialogRef.close();
  }

}
