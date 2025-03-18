import { Component } from '@angular/core';
import { OpslagruimteService } from './Diensten/auth/authServices/opslagruimte/opslagruimte.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  isCustomerLoggedIn: boolean = OpslagruimteService.isCustomerLoggedIn();
  isAdminLoggedIn: boolean = OpslagruimteService.isAdminLoggedIn();

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.router.events.subscribe(event => {
      if (event.constructor.name === "NavigationEnd") {
        this.isCustomerLoggedIn = OpslagruimteService.isCustomerLoggedIn();
        this.isAdminLoggedIn = OpslagruimteService.isAdminLoggedIn();
      }
    })
  }

  logout() {
    OpslagruimteService.signOut();
    this.router.navigateByUrl('login');
  }
}
