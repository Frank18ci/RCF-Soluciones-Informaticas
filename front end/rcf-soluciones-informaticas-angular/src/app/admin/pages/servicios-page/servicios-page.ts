import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import Service from '../../../shared/model/Service';
import { ServiceService } from '../../../shared/services/service-services/service-service';
import { MatDialog } from '@angular/material/dialog';
import { ServiceDialog } from '../../components/dialog/service-dialog/service-dialog';

@Component({
  selector: 'app-servicios-page',
  imports: [MatTableModule, MatPaginatorModule, CommonModule, MatIconModule],
  templateUrl: './servicios-page.html',
  styleUrl: './servicios-page.css'
})
export class ServiciosPage implements OnInit, AfterViewInit{
  displayedColumns: string[] = ['Id', 'Codigo', 'Nombre', 'Descripcion', 'Precio', 'Presencia Personal', 'Activo', 'Acciones'];
  dataSource = new MatTableDataSource<Service>([]);
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private serviceService: ServiceService,
    private dialog: MatDialog
  ) { }
  ngOnInit(): void {
    this.loadServices();
  }
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }
  loadServices() {
    this.serviceService.getServices().subscribe(services => {
      this.dataSource.data = services;
      console.log('Services loaded:', services);
    });
  }
  openDialog(service?: Service) {
    let serviceDialogData: any = {};
    if (service) {
      serviceDialogData = { ...service };
    }
    const dialogRef = this.dialog.open(ServiceDialog, {
      width: '700px',
      data: service ? serviceDialogData : {}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (serviceDialogData.id) {
          this.serviceService.updateService(serviceDialogData.id, result).subscribe({
            next: () => this.loadServices(),
            error: (err) => console.error('Error updating service:', err)
          });
        } else {
          this.serviceService.saveService(result).subscribe({
            next: () => this.loadServices(),
            error: (err) => console.error('Error creating service:', err)
          });
        }
      }
    });
  }
  verServicio(serviceId: number) {
    // Implementation for viewing service details
  }
  deleteServicio(serviceId: number) {
    this.serviceService.deleteService(serviceId).subscribe({
      next: () => this.loadServices(),
      error: (err) => console.error('Error deleting service:', err)
    });
  }
  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.buscarServicioPorNombre(filterValue.trim().toLowerCase());
  }
  buscarServicioPorNombre(name: string) {
    this.serviceService.getServicesByName(name).subscribe(services => {
      this.dataSource.data = services;
    });
  }
}
