import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from 'src/app/admin/service/admin.service';

@Component({
  selector: 'app-vgv-voegen',
  templateUrl: './vgv-voegen.component.html',
  styleUrls: ['./vgv-voegen.component.scss']
})
export class VgvVoegenComponent {

  productId: number = this.activatedRoute.snapshot.params["productId"];
  isSpinning = false;
  FAQForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.FAQForm = this.fb.group({
      question: [null, [Validators.required]],
      answer: [null, [Validators.required]],
    });
  }


  postFAQ(): void {
    this.isSpinning = true;
    this.adminService.postFAQ(this.productId, this.FAQForm.value).subscribe((res) => {
      this.isSpinning = false;
      if (res.id != null) {
        this.snackBar.open('Vraag is toegevoegd!', 'Close', {
          duration: 5000
        });
        this.router.navigateByUrl('/admin/dashboard');
      } else {
        this.snackBar.open("Iets ging mis, probeer aub opnieuw", 'Close', {
          duration: 5000,
          panelClass: 'error-snackbar'
        });
      }
    });
  }


}
