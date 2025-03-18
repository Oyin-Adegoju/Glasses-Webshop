import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { KlantService } from 'src/app/klant/service/klant.service';
import { BestellingPlaatsenComponent } from '../../bestelling-plaatsen/bestelling-plaatsen/bestelling-plaatsen.component';
import { OpslagruimteService } from 'src/app/Diensten/auth/authServices/opslagruimte/opslagruimte.service';

@Component({
  selector: 'app-winkelwagen-artikelen',
  templateUrl: './winkelwagen-artikelen.component.html',
  styleUrls: ['./winkelwagen-artikelen.component.scss']
})
export class WinkelwagenArtikelenComponent {
  
  cartItems: any[] = [];
  order: any;
  couponForm!: FormGroup;

  constructor(private klantservice: KlantService,
    private snackbar: MatSnackBar,
    private fb: FormBuilder,
    public dialog: MatDialog,
    private klantService: KlantService,
    private snackBar: MatSnackBar,) { }

  ngOnInit(): void {
    this.getCart();
  }

 
  getCart() {
    this.cartItems = [];
    this.klantservice.getCartByUserId().subscribe((res) => {
      console.log(res);
      res.cartItems.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.cartItems.push(element);
      });
      this.cartItems = res.cartItems;
      this.order = res;
    });
  }

  increaseQuantity(productId: any) {
    console.log("increase", productId)
    this.klantservice.addPlusOnProduct(productId).subscribe(() => {
      this.snackbar.open('Producthoeveelheid verhoogt.', 'Close', { duration: 5000 });
      this.getCart();
    });
  }

  decreaseQuantity(productId: any) {
    console.log("decrease", productId)
    this.klantservice.addMinusOnProduct(productId).subscribe(() => {
      this.snackbar.open('Producthoeveelheid verlaagt', 'Close', { duration: 5000 });
      this.getCart();
    });
  }

  // Delete item from cart
  // Delete item from cart
  deleteItem(productId: any): void {
    const userIdString = OpslagruimteService.getUserId(); // This returns a string
    const userId = Number(userIdString);
    if (userId) {
      this.klantService.removeProductFromCart(userId, productId).subscribe({
        next: () => {
          this.snackbar.open('Item removed from cart.', 'Close', { duration: 3000 });
          this.getCart(); // Refresh cart contents
        },
        error: (error) => {
          console.error('Error removing item from cart:', error);
          this.snackbar.open('Failed to remove item from cart.', 'Close', { duration: 3000 });
        }
      });
    } else {
      this.snackbar.open('User ID not found.', 'Close', { duration: 3000 });
    }
  }


  placeOrder(): void {
    this.dialog.open(BestellingPlaatsenComponent);
  }

  
paginaVergroten(): void {
  let currentFontSize = parseFloat(getComputedStyle(document.documentElement).fontSize);
  document.documentElement.style.fontSize = `${currentFontSize + 1}px`;
}

paginaVerkleinen(): void {
  let currentFontSize = parseFloat(getComputedStyle(document.documentElement).fontSize);
  if (currentFontSize > 10) { 
      document.documentElement.style.fontSize = `${currentFontSize - 1}px`;
  }
}


  
  

}
