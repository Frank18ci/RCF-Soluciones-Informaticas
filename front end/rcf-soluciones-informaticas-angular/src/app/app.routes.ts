import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'tienda',
        loadChildren: () => import('./tienda/tienda-module').then(m => m.TiendaModule)
    },
    {
        path: 'admin',
        loadChildren: () => import('./admin/admin-module').then(m => m.AdminModule)
    },
    {
        path: 'auth',
        loadChildren: () => import('./auth/auth-module').then(m => m.AuthModule)
    },
    {
        path: '**',
        redirectTo: 'admin',
        pathMatch: 'full'
    }
];
