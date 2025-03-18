import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../Diensten/auth/authServices/authService/auth.service';
import { OpslagruimteService } from '../Diensten/auth/authServices/opslagruimte/opslagruimte.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginForm!: FormGroup;
  isSpinning: boolean = false;
  hidePassword = true;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private snackBar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required]],
    });
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit(): void {
    this.isSpinning = true;

    for (const i in this.loginForm.controls) {
      this.loginForm.controls[i].markAsDirty();
      this.loginForm.controls[i].updateValueAndValidity();
    }

    const username = this.loginForm.get('email')!.value;
    const password = this.loginForm.get('password')!.value;

    this.authService.login(username, password).subscribe(
      (res) => {
        this.isSpinning = false;


        if (OpslagruimteService.isAdminLoggedIn()) {
          this.router.navigateByUrl('admin/dashboard');
        } else if (OpslagruimteService.isCustomerLoggedIn()) {
          this.router.navigateByUrl('customer/dashboard');
        }

        console.log('res', res);
      },
      (error) => {
        console.log('errorr', error);
        this.isSpinning = false;

        if (error.status === 406) {
          this.snackBar.open(
            'Gebruiker is niet aktief, verifeer aub de email.',
            'ERROR',
            { duration: 5000 }
          );
        } else {
          this.snackBar.open('Gegevens kloppen niet, probeer aub opnieuw', 'ERROR', { duration: 5000 });
        }
      }
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
