import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../Diensten/auth/authServices/authService/auth.service';

@Component({
  selector: 'app-pakket-traceren',
  templateUrl: './pakket-traceren.component.html',
  styleUrls: ['./pakket-traceren.component.scss']
})
export class PakketTracerenComponent {
  
  order: any;
  isSpinning: boolean;
  searchOrderForm!: FormGroup;

  constructor(
    private authService: AuthService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.searchOrderForm = this.fb.group({
      trackingId: [null, [Validators.required]],
    });
  }

  submitForm() {
    this.isSpinning = true;
    console.log(this.searchOrderForm.get('trackingId').value);
    this.authService.getOrderByTrackingId(this.searchOrderForm.get('trackingId').value).subscribe((res) => {
      this.isSpinning = false;
      this.order = res;
      console.log(this.order);
    })
  }

  

}


