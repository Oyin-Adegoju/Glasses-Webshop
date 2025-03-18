import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { OpslagruimteService } from '../../../authServices/opslagruimte/opslagruimte.service';


@Injectable({
  providedIn: 'root'
})
export class NoAuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {

    if(OpslagruimteService.hasToken() && OpslagruimteService.isCustomerLoggedIn()){
      this.router.navigateByUrl('/customer/dashboard');
      return false;
    }
    else if (OpslagruimteService.hasToken() && OpslagruimteService.isAdminLoggedIn()){
      this.router.navigateByUrl('/admin/dashboard');
      return false;
    }
    return true;
  }
  
  
}