import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioPage } from './pages/inicio-page/inicio-page';
import { LayoutPage } from './pages/layout-page/layout-page';
import { ContactanosPage } from './pages/contactanos-page/contactanos-page';
import { ProductosPage } from './pages/productos-page/productos-page';
import { ServiciosPage } from './pages/servicios-page/servicios-page';

const routes: Routes = [
  {
    path: '',
    component: LayoutPage,
    children: [
      {
        path: 'inicio',
        component: InicioPage
      },
      {
        path: 'productos',
        component: ProductosPage
      },
      {
        path: 'servicios',
        component: ServiciosPage
      },
      {
        path: 'contactenos',
        component: ContactanosPage
      },
      {
        path: '**',
        redirectTo: 'inicio'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TiendaRoutingModule { }
