import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { KlantRoutingModule } from './klant-routing.module';
import { WinkelwagenArtikelenComponent } from './components/winkelwagen-artikelen/winkelwagen-artikelen/winkelwagen-artikelen.component';
import { WachtwoordVeranderenComponent } from './components/wachtwoord-veranderen/wachtwoord-veranderen/wachtwoord-veranderen.component';
import { DashboardComponent } from './components/dashboard/dashboard/dashboard.component';
import { MijnWenslijstComponent } from './components/mijn-wenslijst/mijn-wenslijst/mijn-wenslijst.component';
import { MijnBestellingenComponent } from './components/mijn-bestellingen/mijn-bestellingen/mijn-bestellingen.component';
import { BestellingPlaatsenComponent } from './components/bestelling-plaatsen/bestelling-plaatsen/bestelling-plaatsen.component';
import { ProfielComponent } from './components/profiel/profiel/profiel.component';
import { BeordeelProductComponent } from './components/beordeel-product/beordeel-product/beordeel-product.component';
import { BekijkProductComponent } from './components/bekijk-product/bekijk-product/bekijk-product.component';
import { BekijkBestellingComponent } from './components/bekijk-bestelling/bekijk-bestelling/bekijk-bestelling.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Angulardemo } from '../Angulardemo';


@NgModule({
  declarations: [
    WinkelwagenArtikelenComponent,
    WachtwoordVeranderenComponent,
    DashboardComponent,
    MijnWenslijstComponent,
    MijnBestellingenComponent,
    BestellingPlaatsenComponent,
    ProfielComponent,
    BeordeelProductComponent,
    BekijkProductComponent,
    BekijkBestellingComponent
  ],
  imports: [
    CommonModule,
    KlantRoutingModule,
    Angulardemo,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class KlantModule { }
