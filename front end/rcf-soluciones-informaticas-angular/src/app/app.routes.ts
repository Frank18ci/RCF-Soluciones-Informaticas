import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'tienda',
        loadChildren: () => import('./tienda/tienda-routing-module').then(m => m.TiendaRoutingModule)
    },
    {
        path: 'admin',
        loadChildren: () => import('./admin/admin-routing-module').then(m => m.AdminRoutingModule)
    },
    {
        path: '**',
        redirectTo: 'admin',
        pathMatch: 'full'
    }
];
