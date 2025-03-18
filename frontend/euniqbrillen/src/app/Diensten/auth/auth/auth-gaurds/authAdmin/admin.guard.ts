import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { OpslagruimteService } from '../../../authServices/opslagruimte/opslagruimte.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private router: Router, private snackBar: MatSnackBar) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {

    if (OpslagruimteService.isCustomerLoggedIn()) {
      this.router.navigateByUrl('/customer/dashboard');
      this.snackBar.open(`Jij hebt geen toegang tot deze pagina!`, 'ERROR', {
        duration: 5000
      });
      return false;
    } else if (!OpslagruimteService.hasToken()) {
      OpslagruimteService.signOut();
      this.router.navigateByUrl('/login');
      this.snackBar.open('Jij bent niet ingelogd. AUB log eerst in!', 'ERROR', {
        duration: 5000
      });
      return false;
    }

    return true;
}}
