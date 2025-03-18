import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerGuard } from '../Diensten/auth/auth/auth-gaurds/authKlant/customer.guard';
import { DashboardComponent } from './components/dashboard/dashboard/dashboard.component';
import { ProfielComponent } from './components/profiel/profiel/profiel.component';
import { WachtwoordVeranderenComponent } from './components/wachtwoord-veranderen/wachtwoord-veranderen/wachtwoord-veranderen.component';
import { MijnBestellingenComponent } from './components/mijn-bestellingen/mijn-bestellingen/mijn-bestellingen.component';
import { BestellingPlaatsenComponent } from './components/bestelling-plaatsen/bestelling-plaatsen/bestelling-plaatsen.component';
import { BeordeelProductComponent } from './components/beordeel-product/beordeel-product/beordeel-product.component';
import { MijnWenslijstComponent } from './components/mijn-wenslijst/mijn-wenslijst/mijn-wenslijst.component';
import { BekijkBestellingComponent } from './components/bekijk-bestelling/bekijk-bestelling/bekijk-bestelling.component';
import { BekijkProductComponent } from './components/bekijk-product/bekijk-product/bekijk-product.component';
import { WinkelwagenArtikelenComponent } from './components/winkelwagen-artikelen/winkelwagen-artikelen/winkelwagen-artikelen.component';

const routes: Routes = [
  {path : 'dashboard', component : DashboardComponent, canActivate:[CustomerGuard]},
  {path : 'profile', component : ProfielComponent,  canActivate:[CustomerGuard]},
  {path : 'change_password', component : WachtwoordVeranderenComponent,  canActivate:[CustomerGuard]},
  { path: 'cart', component: WinkelwagenArtikelenComponent, canActivate: [CustomerGuard] },
  { path: 'my_orders', component: MijnBestellingenComponent, canActivate: [CustomerGuard] },
  { path: 'place-order', component: BestellingPlaatsenComponent, canActivate: [CustomerGuard] },
  { path: 'ordered_products/:orderId', component: BekijkBestellingComponent, canActivate: [CustomerGuard] },
  { path: 'review/:productId', component: BeordeelProductComponent, canActivate: [CustomerGuard] },
  { path: 'product/:productId', component: BekijkProductComponent, canActivate: [CustomerGuard] },
  { path: 'wishlist', component: MijnWenslijstComponent, canActivate: [CustomerGuard] },
];
 

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class KlantRoutingModule { }
