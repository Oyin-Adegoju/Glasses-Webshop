import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/admin/service/admin.service';

@Component({
  selector: 'app-voeg-categorie',
  templateUrl: './voeg-categorie.component.html',
  styleUrls: ['./voeg-categorie.component.scss']
})
export class VoegCategorieComponent {

  isSpinning = false;
  categoryForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService
  ) { }

  ngOnInit(): void {
    this.categoryForm = this.fb.group({
      name: [null, [Validators.required]],
      description: [null, [Validators.required]],
    });
  }


  addCategory(): void {
    if (this.categoryForm.valid) {
      this.isSpinning = true;
      this.adminService.addCategory(this.categoryForm.value).subscribe((res) => {
        this.isSpinning = false;
        if (res.id != null) {
          this.snackBar.open('Categorie sucesvol toegevoegd!', 'Close', {
            duration: 5000
          });
          this.router.navigateByUrl('/admin/dashboard');
        } else {
          this.snackBar.open(res.message, 'Close', {
            duration: 5000,
            panelClass: 'error-snackbar'
          });
        }
      });
    } else {
      this.categoryForm.markAllAsTouched();
    }
  }

}
