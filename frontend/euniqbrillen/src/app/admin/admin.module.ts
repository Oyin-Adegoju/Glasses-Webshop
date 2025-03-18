import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';

import { Angulardemo } from '../Angulardemo';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard/admin-dashboard.component';
import { BestellingenComponent } from './components/bestellingen/bestellingen/bestellingen.component';
import { ProductUpdatenComponent } from './components/product-updaten/product-updaten/product-updaten.component';
import { VgvVoegenComponent } from './components/vgv-voegen/vgv-voegen/vgv-voegen.component';
import { VoegCategorieComponent } from './components/voeg-categorie/voeg-categorie/voeg-categorie.component';
import { VoegProductComponent } from './components/voeg-product/voeg-product/voeg-product.component';
import { BestellingStatusComponent } from './components/bestelling-status/bestelling-status/bestelling-status.component';


@NgModule({
  declarations: [
    AdminDashboardComponent,
    BestellingenComponent,
    ProductUpdatenComponent,
    VgvVoegenComponent,
    VoegCategorieComponent,
    VoegProductComponent,
    BestellingStatusComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    Angulardemo,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
   
    
  ]
})
export class AdminModule { }
