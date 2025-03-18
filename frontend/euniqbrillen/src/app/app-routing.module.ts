import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AanmeldenComponent } from './aanmelden/aanmelden.component';
import { LoginComponent } from './login/login.component';
import { NoAuthGuard } from './Diensten/auth/auth/auth-gaurds/noauth/no-auth.guard';
import { PakketTracerenComponent } from './pakket-traceren/pakket-traceren.component';

const routes: Routes = [
  {path:'login',component:LoginComponent, canActivate:[NoAuthGuard]},
  {path:'register',component:AanmeldenComponent, canActivate:[NoAuthGuard] },
  {path: 'order', component: PakketTracerenComponent, canActivate: [NoAuthGuard] },
  {path:'admin', loadChildren:()=>import('./admin/admin.module').then(m=>m.AdminModule)},
  {path:'customer', loadChildren:()=>import('./klant/klant.module').then(m=>m.KlantModule)},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
