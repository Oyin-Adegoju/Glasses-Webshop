import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { Angulardemo } from './Angulardemo';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { LoginComponent } from './login/login.component';
import { AanmeldenComponent } from './aanmelden/aanmelden.component';
import { PakketTracerenComponent } from './pakket-traceren/pakket-traceren.component';





@NgModule({
  declarations: [
    AppComponent,
    AanmeldenComponent,
    LoginComponent,
    PakketTracerenComponent
  ],
  imports: [
    BrowserModule,
    Angulardemo,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
