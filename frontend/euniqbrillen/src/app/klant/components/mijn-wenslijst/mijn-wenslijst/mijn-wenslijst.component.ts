import { Component } from '@angular/core';
import { KlantService } from 'src/app/klant/service/klant.service';

@Component({
  selector: 'app-mijn-wenslijst',
  templateUrl: './mijn-wenslijst.component.html',
  styleUrls: ['./mijn-wenslijst.component.scss']
})
export class MijnWenslijstComponent {
  products: any[] = [];

  constructor(
    private klantService: KlantService,
  ) { }

  ngOnInit() {
    this.getWishlistByUserId();
  }

  getWishlistByUserId() {
    this.klantService.getWishlistByUserId().subscribe(
      (res) => {
        console.log(res);
        // this.products = res;
        res.forEach(element => {
          element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
          this.products.push(element);
        });
      }
    )
  }

  removeProductFromWishlist(productId: number) {
    this.klantService.removeProductFromWishlist(productId).subscribe(
      () => {
        // Remove the product from the products array without needing to fetch the wishlist again.
        this.products = this.products.filter(product => product.id !== productId);
        console.log(`Product with ID ${productId} sucesvol verwijdert.`);
      },
      (error) => console.error(`Error removing product with ID ${productId}:`, error)
    );
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
