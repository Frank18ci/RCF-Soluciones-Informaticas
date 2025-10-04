import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutPage } from './pages/layout-page/layout-page';
import { DashboardPage } from './pages/dashboard-page/dashboard-page';
import { ProductosPage } from './pages/productos-page/productos-page';
import { OrdenesPage } from './orders/ordenes-page/ordenes-page';
import { ClientesPage } from './orders/clientes-page/clientes-page';

const routes: Routes = [
  {
    path: '',
    component: LayoutPage,
    children: [
      {
        path: 'dashboard',
        component: DashboardPage
      },
      {
        path: 'products',
        component: ProductosPage
      },
      {
        path: 'orders',
        component: OrdenesPage
      },
      {
        path: 'customers',
        component: ClientesPage
      },
      {
        path: '**',
        redirectTo: 'dashboard'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
