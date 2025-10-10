import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import ServiceRequest from '../../../../shared/model/Service-Service/ServiceRequest.model';
import Service from '../../../../shared/model/Service';

@Component({
  selector: 'app-service-dialog',
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
  templateUrl: './service-dialog.html',
  styleUrl: './service-dialog.css'
})
export class ServiceDialog implements OnInit{
  form!: FormGroup;
  serviceRequest: ServiceRequest = {
    code: '',
    name: '',
    description: '',
    basePriceCents: 0,
    taxRate: 0,
    durationMinutes: 0,
    requiresOnSite: false,
    active: true
  }
  serviceId?: number;
  constructor(
    private dialogRef: MatDialogRef<ServiceDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Service,
    private fb: FormBuilder
  ) {
    if(data){
      this.serviceRequest = {
        code: data.code,
        name: data.name,
        description: data.description,
        basePriceCents: data.basePriceCents,
        taxRate: data.taxRate,
        durationMinutes: data.durationMinutes,
        requiresOnSite: data.requiresOnSite,
        active: data.active
      };
      this.serviceId = data.id;
    }
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      code: [this.serviceRequest.code, [Validators.required, Validators.maxLength(20)]],
      name: [this.serviceRequest.name, [Validators.required, Validators.maxLength(100)]],
      description: [this.serviceRequest.description, [Validators.required, Validators.maxLength(500)]],
      basePriceCents: [this.serviceRequest.basePriceCents, [Validators.required, Validators.min(0)]],
      taxRate: [this.serviceRequest.taxRate, [Validators.required, Validators.min(0), Validators.max(100)]],
      durationMinutes: [this.serviceRequest.durationMinutes, [Validators.required, Validators.min(0)]],
      requiresOnSite: [this.serviceRequest.requiresOnSite, [Validators.required]],
      active: [this.serviceRequest.active, [Validators.required]]
    });
  }
  saveService() {
    if (this.form.valid) {
      const updatedService: ServiceRequest = {
        ...this.serviceRequest,
        ...this.form.value,
        basePriceCents: Math.round(Number(this.form.value.basePriceCents) * 100)
      };
      this.dialogRef.close(updatedService);
    }
  }
  closeDialog() {
    this.dialogRef.close();
  }
}
