import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutPage } from './pages/layout-page/layout-page';
import { DashboardPage } from './pages/dashboard-page/dashboard-page';
import { ProductosPage } from './pages/productos-page/productos-page';
import { OrdenesPage } from './pages/ordenes-page/ordenes-page';
import { ClientesPage } from './pages/clientes-page/clientes-page';
import { CategoriasPage } from './pages/categorias-page/categorias-page';
import { DescuentosPage } from './pages/descuentos-page/descuentos-page';
import { ServiciosPage } from './pages/servicios-page/servicios-page';

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
        path: 'services',
        component: ServiciosPage
      },
      {
        path: 'categories',
        component: CategoriasPage
      },
      {
        path: 'discounts',
        component: DescuentosPage
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
