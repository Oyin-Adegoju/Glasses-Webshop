import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VoegCategorieComponent } from './components/voeg-categorie/voeg-categorie/voeg-categorie.component';
import { VoegProductComponent } from './components/voeg-product/voeg-product/voeg-product.component';
import { ProductUpdatenComponent } from './components/product-updaten/product-updaten/product-updaten.component';
import { VgvVoegenComponent } from './components/vgv-voegen/vgv-voegen/vgv-voegen.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard/admin-dashboard.component';
import { AdminGuard } from '../Diensten/auth/auth/auth-gaurds/authAdmin/admin.guard';
import { BestellingenComponent } from './components/bestellingen/bestellingen/bestellingen.component';

const routes: Routes = [
  { path: 'dashboard', component: AdminDashboardComponent, canActivate: [AdminGuard] },
  { path: 'category', component: VoegCategorieComponent, canActivate: [AdminGuard] },
  { path: 'product', component: VoegProductComponent, canActivate: [AdminGuard] },
  { path: 'product/:productId', component: ProductUpdatenComponent, canActivate: [AdminGuard] },
  { path: 'orders', component: BestellingenComponent, canActivate: [AdminGuard] },
  { path: 'faq/:productId', component: VgvVoegenComponent, canActivate: [AdminGuard] },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class AdminRoutingModule { }
