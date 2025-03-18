import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { KlantService } from 'src/app/klant/service/klant.service';

@Component({
  selector: 'app-bestelling-plaatsen',
  templateUrl: './bestelling-plaatsen.component.html',
  styleUrls: ['./bestelling-plaatsen.component.scss']
})
export class BestellingPlaatsenComponent {
  orderForm!: FormGroup;
  Payment: string[] = ["Betalen aan de deur"];

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private klantService: KlantService,
    private router: Router,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.orderForm = this.fb.group({
      address: [null, [Validators.required]],
      orderDescription: [null],
    });
  }

  placeOrder() {
    this.klantService.placeOrder(this.orderForm.value).subscribe((res) => {
      if (res.id != null) {
        this.snackBar.open("Bestelling voltoid!", "Close", { duration: 5000 })
        this.closeForm();
        this.router.navigateByUrl("/customer/my_orders");
      } else {
        this.snackBar.open("Iets gaat mis, probeer aub opnieuw", "Close", { duration: 5000 })
      }
    });
  }


  closeForm() {
    this.dialog.closeAll();
  }


}
