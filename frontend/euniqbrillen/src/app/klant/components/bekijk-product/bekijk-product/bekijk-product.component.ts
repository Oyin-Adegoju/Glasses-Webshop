import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { OpslagruimteService } from 'src/app/Diensten/auth/authServices/opslagruimte/opslagruimte.service';
import { KlantService } from 'src/app/klant/service/klant.service';

@Component({
  selector: 'app-bekijk-product',
  templateUrl: './bekijk-product.component.html',
  styleUrls: ['./bekijk-product.component.scss']
})
export class BekijkProductComponent {
  productId: number = this.activatedRoute.snapshot.params["productId"];
  isSpinning: boolean = false;
  reviewForm!: FormGroup;
  product: any;
  FAQS: any[] = [];
  reviews: any[] = [];
  

  constructor(
    private snackBar: MatSnackBar,
    private klantService: KlantService,
    private activatedRoute: ActivatedRoute
  ) { }

  
  
  ngOnInit(): void {
    this.getCompleteProductDetailById();
  }

  addToWishlist(): void {
    this.isSpinning = true;
    const wishlistDto = {
      productId: this.productId,
      userId: OpslagruimteService.getUserId(),
    }
    this.klantService.addProductToWishlist(wishlistDto).subscribe((res) => {
      this.isSpinning = false;
      if (res.id != null) {
        this.snackBar.open('Product is sucesvol toegevoegd aan winkel wagen!', 'Close', {
          duration: 5000
        });
      } else {
        this.snackBar.open("Product staat al in wensLijst", 'ERROR', {
          duration: 5000
        });
      }
    });
  }

  getCompleteProductDetailById() {
    this.klantService.getCompleteProductDetailById(this.productId).subscribe(
      (res) => {
        console.log(res);
        this.product = res.productDto;
        if (res.productDto.returnedImg != null) {
          this.product.processedImg = 'data:image/png;base64,' + res.productDto.returnedImg;
        }
        this.FAQS = res.faqDtoList;
        // this.Reviews = res.reviewDtoList;
        res.reviewDtoList.forEach(element => {
          element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
          this.reviews.push(element);
        });
      }
    );
  }
}
