import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { OpslagruimteService } from '../../../authServices/opslagruimte/opslagruimte.service';


@Injectable({
  providedIn: 'root'
})
export class CustomerGuard implements CanActivate {

  constructor(private router: Router, private snackBar: MatSnackBar) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {

    if (OpslagruimteService.isAdminLoggedIn()) {
      this.router.navigateByUrl('/admin/dashboard');
      this.snackBar.open(`You don't have access to this page!`, 'ERROR', {
        duration: 5000
      });
      return false;
    } else if (!OpslagruimteService.hasToken()) {
      OpslagruimteService.signOut();
      this.router.navigateByUrl('/login');
      this.snackBar.open('You are not logged in. Please login first!', 'ERROR', {
        duration: 5000
      });
      return false;
    }

    return true;
  }
}