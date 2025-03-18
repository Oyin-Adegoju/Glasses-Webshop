import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/Diensten/auth/authServices/authService/auth.service';

@Component({
  selector: 'app-wachtwoord-veranderen',
  templateUrl: './wachtwoord-veranderen.component.html',
  styleUrls: ['./wachtwoord-veranderen.component.scss']
})

export class WachtwoordVeranderenComponent {
  changePasswordForm!: FormGroup;
  isSpinning = false;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.changePasswordForm = this.fb.group({
      oudWachtwoord: [null, [Validators.required]],
      nieuwWachtwoord: [null, [Validators.required]],
      bevestigWachtwoord: [null, [Validators.required]],
    });
  }

  onSubmit(): void {

    if (this.changePasswordForm.valid) {
      const nieuwWachtwoord = this.changePasswordForm.get('nieuwWachtwoord')?.value;
      const bevestigWachtwoord = this.changePasswordForm.get('bevestigWachtwoord')?.value;

      if (nieuwWachtwoord !== bevestigWachtwoord) {
        this.snackBar.open('Wachtwoorden komen niet overheen.', 'Close', { duration: 5000, panelClass: 'error-snackbar' });
        return;
      }

      this.isSpinning = true;

      this.authService.updatePassword(this.changePasswordForm.value).subscribe(
        (response) => {
          this.isSpinning = false;
          console.log(response);
          if (response.id != null) {
            this.snackBar.open('Wachtwoord is veranderd!', 'Close', { duration: 5000 });
          } else {
            this.snackBar.open(' Oud wachtwoord is fout', 'Close', { duration: 5000 });
            this.changePasswordForm.reset();
          }

        },
        (error) => {
          this.isSpinning = false;
          this.snackBar.open('Mislukt, probeer aub opnieuw', 'Close', { duration: 5000, panelClass: 'error-snackbar' });
        }
      );
    } else {
      for (const i in this.changePasswordForm.controls) {
        this.changePasswordForm.controls[i].markAsDirty();
        this.changePasswordForm.controls[i].updateValueAndValidity();
      }
    }
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
