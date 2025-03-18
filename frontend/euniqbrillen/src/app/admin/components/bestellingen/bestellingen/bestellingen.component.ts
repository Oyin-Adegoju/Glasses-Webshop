import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from 'src/app/admin/service/admin.service';

@Component({
  selector: 'app-bestellingen',
  templateUrl: './bestellingen.component.html',
  styleUrls: ['./bestellingen.component.scss']
})
export class BestellingenComponent {

  isSpinning = false;
  Orders: any;

  constructor(
    private adminService: AdminService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.getPlacedOrders();
  }

  getPlacedOrders() {
    this.adminService.getPlacedOrders().subscribe((res) => {
      this.Orders = res;
      console.log(res);
    })
  }

  changeOrderStatus(orderId: number, status: string) {
    console.log('Status is:', status);
    this.adminService.changeOrderStatus(orderId, status).subscribe(
      (res) => {
        console.log(res);
        if (res.id != null) {
          this.snackBar.open("Bestelling status veranderd", "Close", { duration: 5000 });
          this.getPlacedOrders();
        } else {
          this.snackBar.open("Iets ging mis", "Close", { duration: 5000 });
        }
      })
  }

}
