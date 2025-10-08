import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import UserRequest from '../../../../shared/model/User-Service/UserRequest.model';
import RolResponse from '../../../../shared/model/RolResponse';
import User from '../../../../shared/model/User';
import { RoleService } from '../../../../shared/services/user-services/role-service';

@Component({
  selector: 'app-user-dialog',
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
  templateUrl: './user-dialog.html',
  styleUrl: './user-dialog.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserDialog implements OnInit {
  form!: FormGroup;
  userRequest: UserRequest = {
    fullName: '',
    email: '',
    password: '',
    roleId: 0,
    phone: '',
    active: true
  }
  userId?: number;
  roles: RolResponse[] = [];
  constructor(
    private dialogRef: MatDialogRef<UserDialog>,
    @Inject(MAT_DIALOG_DATA) public data: User,
    private fb: FormBuilder,
    private roleService: RoleService,
    private cdRef: ChangeDetectorRef
  ) {
    if(data){
      this.userRequest = {
        fullName: data.fullName ?? '',
        email: data.email ?? '',
        password: data.password ?? '',
        roleId: data.role?.id ?? 0,
        phone: data.phone ?? '',
        active: data.active ?? true
      }
      this.userId = data.id ?? undefined;
    }
   }
  ngOnInit(): void {
    this.form = this.fb.group({
      fullName: [this.userRequest.fullName, [Validators.required, Validators.minLength(2)]],
      email: [this.userRequest.email, [Validators.required, Validators.email]],
      password: [this.userRequest.password, [Validators.required, Validators.minLength(6)]],
      roleId: [this.userRequest.roleId, [Validators.required]],
      phone: [this.userRequest.phone, [Validators.required, Validators.maxLength(9), Validators.minLength(9)]],
      active: [this.userRequest.active, [Validators.required]]
    });

    this.roleService.getRoles().subscribe({
      next: (roles) => {
        this.roles = roles;
        if(this.userRequest.roleId != 0) {
          this.form.patchValue({ roleId: this.userRequest.roleId });
        }
        this.cdRef.markForCheck();
      },
      error: (err) => {
        console.error('Error fetching roles:', err);
      }
    });
  }
  saveUser() {
    if (this.form.valid) {
      const userData: UserRequest = {
        ...this.userRequest,
        ...this.form.value
      };
      this.dialogRef.close(userData);
    }
  }
  closeDialog() {
    this.dialogRef.close();
  }
}
