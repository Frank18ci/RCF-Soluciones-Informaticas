import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import User from '../../../shared/model/User';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { UserService } from '../../../shared/services/user-services/user-service';
import { MatDialog } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { UserDialog } from '../../components/dialog/user-dialog/user-dialog';

@Component({
  selector: 'app-clientes-page',
  imports: [MatTableModule, MatPaginatorModule, CommonModule, MatIconModule],
  templateUrl: './clientes-page.html',
  styleUrl: './clientes-page.css'
})
export class ClientesPage implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['Id', 'Correo', 'Nombre Completo', 'Rol', 'Celular', 'Activo', 'Acciones'];
  dataSource = new MatTableDataSource<User>([]);
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  constructor(
    private userService: UserService,
    private dialog: MatDialog
  ){}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getUsers().subscribe(users => {
      this.dataSource.data = users;
    });
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }
  openDialog(user?: User): void {
    let userDialogData: any = {};
    if (user) {
      userDialogData = { ...user };
    }
    const dialogRef = this.dialog.open(UserDialog, {
      width: '700px',
      data: user ? userDialogData : {}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (userDialogData.id) {
          this.userService.updateUser(userDialogData.id, result).subscribe({
            next: () => this.loadUsers(),
            error: (err) => console.error('Error al actualizar usuario:', err)
          });
        } else {
          this.userService.createUser(result).subscribe({
            next: () => this.loadUsers(),
            error: (err) => console.error('Error al crear usuario:', err)
          });
        }
      }
    });
  }

  verUser(userId: number): void {
    this.userService.getUserById(userId).subscribe(user => {
      console.log(user);
    });
  }

  deleteUser(userId: number): void {
    if(confirm('¿Estás seguro de que deseas eliminar este usuario?')){
      this.userService.deleteUser(userId).subscribe(() => {
        this.loadUsers();
      });
    }
  }
}
