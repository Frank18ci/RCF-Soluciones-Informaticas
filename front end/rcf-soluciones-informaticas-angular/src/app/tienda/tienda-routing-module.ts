import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioPage } from './pages/inicio-page/inicio-page';
import { LayoutPage } from './pages/layout-page/layout-page';

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
