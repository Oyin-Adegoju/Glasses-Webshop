import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from 'src/app/admin/service/admin.service';

@Component({
  selector: 'app-product-updaten',
  templateUrl: './product-updaten.component.html',
  styleUrls: ['./product-updaten.component.scss']
})
export class ProductUpdatenComponent {

  productId: any = this.activatedroute.snapshot.params['productId'];
  listOfCategories: any = [];
  isSpinning = false;
  updateProductForm!: FormGroup;
  imgChanged = false;
  selectedFile: any;
  imagePreview: string | ArrayBuffer | null = null;
  existingImage: string | null = null;

  constructor(private fb: FormBuilder,
    private router: Router,
    private snack: MatSnackBar,
    private adminService: AdminService,
    private activatedroute: ActivatedRoute,) { }

  ngOnInit(): void {
    this.updateProductForm = this.fb.group({
      categoryId: [null, [Validators.required]],
      name: [null, [Validators.required]],
      description: [null, [Validators.required]],
      price: [null, [Validators.required]],
    });
    this.getProductById();
    this.getAllCategories();
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
    this.imgChanged = true;
    this.existingImage = null;
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile);
  }

  getProductById() {
    this.adminService.getProductById(this.productId).subscribe((res) => {
      const productDto = res;
      this.existingImage = 'data:image/jpeg;base64,' + res.returnedImg;
      this.updateProductForm.patchValue(productDto);
    })
  }

  getAllCategories() {
    this.adminService.getAllCategories().subscribe((res) => {
      this.listOfCategories = res;
    })
  }

  updateProduct(): void {
    if (this.updateProductForm.valid) {
      this.isSpinning = true;
      const formData: FormData = new FormData();
      if (this.imgChanged && this.selectedFile) {
        formData.append('img', this.selectedFile);
      }
      formData.append('categoryId', this.updateProductForm.get('categoryId').value);
      formData.append('price', this.updateProductForm.get('price').value);
      formData.append('name', this.updateProductForm.get('name').value);
      formData.append('description', this.updateProductForm.get('description').value);

      this.adminService.updateProduct(this.productId, formData).subscribe((res) => {
        this.isSpinning = false;
        if (res.id != null) {
          this.snack.open('Product sucesvol geupdate!', 'Close', {
            duration: 5000
          });
          this.router.navigateByUrl('/admin/dashboard');
        } else {
          this.snack.open(`${res.message}`, 'Close', {
            duration: 5000
          });
        }
      });
    } else {
      for (const i in this.updateProductForm.controls) {
        this.updateProductForm.controls[i].markAsDirty();
        this.updateProductForm.controls[i].updateValueAndValidity();
      }
      this.snack.open('Vul aub alle verplichte velden in.', 'Close', {
        duration: 5000
      });
    }
  }


}
