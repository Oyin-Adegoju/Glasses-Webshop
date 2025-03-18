import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { OpslagruimteService } from 'src/app/Diensten/auth/authServices/opslagruimte/opslagruimte.service';
import { KlantService } from 'src/app/klant/service/klant.service';

@Component({
  selector: 'app-beordeel-product',
  templateUrl: './beordeel-product.component.html',
  styleUrls: ['./beordeel-product.component.scss']
})
export class BeordeelProductComponent {
  
  productId: number = this.activatedRoute.snapshot.params["productId"];
  isSpinning: boolean = false;
  reviewForm!: FormGroup;
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile);
  }

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private klantService: KlantService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.reviewForm = this.fb.group({
      rating: [null, [Validators.required]],
      description: [null, [Validators.required]],
    });
  }

  submitForm(): void {
    this.isSpinning = true;
    const formData: FormData = new FormData();
    formData.append('img', this.selectedFile);
    formData.append('productId', this.productId.toString());
    formData.append('userId', OpslagruimteService.getUserId().toString());
    formData.append('rating', this.reviewForm.get('rating').value);
    formData.append('description', this.reviewForm.get('description').value);
    this.klantService.giveReview(formData).subscribe((res) => {
      this.isSpinning = false;
      if (res.id != null) {
        this.snackBar.open('Jouw recensie staat nu online!', 'Close', {
          duration: 5000
        });
        this.router.navigateByUrl('/customer/my_orders');
      } else {
        this.snackBar.open("Ons excuses iets ging mis", 'ERROR', {
          duration: 5000
        });
      }
    });
  }

  paginaVergroten(): void {
    let currentFontSize = parseFloat(getComputedStyle(document.documentElement).fontSize);
    document.documentElement.style.fontSize = `${currentFontSize + 1}px`;
  }
 
  paginaVerkleinen(): void {
    let currentFontSize = parseFloat(getComputedStyle(document.documentElement).fontSize);
    if (currentFontSize > 10) { 
        document.documentElement.style.fontSize = `${currentFontSize - 1}px`;
      }  }


}
