import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/Diensten/auth/authServices/authService/auth.service';
import { OpslagruimteService } from 'src/app/Diensten/auth/authServices/opslagruimte/opslagruimte.service';

@Component({
  selector: 'app-profiel',
  templateUrl: './profiel.component.html',
  styleUrls: ['./profiel.component.scss']
})
export class ProfielComponent {
  
  newUpdatedSelectedFile: string;
  myFile: FileList;
  isSpinning = false;
  listOfOption: Array<{ label: string; value: string }> = [];
  listOfTagOptions = [];
  existingProfileImage: any;
  validateForm!: FormGroup;
  imgChanged: boolean;
  avatarImage: any = 'src\assets\avatar.png';

  constructor(private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router,
    private authService: AuthService,
    private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      email: ['', Validators.required],
      name: ['', Validators.required]
    });
    this.getUserData();
  }

  getUserData() {
    this.authService.getUserById().subscribe(res => {
      console.log(res);
      this.validateForm.patchValue(res);
      if (res.returnedImg != null) {
        this.existingProfileImage = 'data:image/png;base64,' + res.returnedImg;
      }
    })
  }

  onFileSelected(event: any) {
    this.newUpdatedSelectedFile = event.target.files[0];
    const file = event.target.files[0];
    this.imgChanged = true;
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.existingProfileImage = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }

  submitForm(): void {
    if (this.validateForm.valid) {
      const formData: FormData = new FormData();
      if (this.imgChanged && this.newUpdatedSelectedFile) {
        formData.append('img', this.newUpdatedSelectedFile);
      }
      formData.append('name', this.validateForm.get('name').value);
      formData.append('email', this.validateForm.get('email').value);
      formData.append('id', OpslagruimteService.getUserId());
      console.log(formData);
      this.authService.updateUser(formData).subscribe((res) => {
        if (res.id != null) {
          this.snackBar.open(`Profiel sucesvol geupdate!`, 'Close', {
            duration: 5000,
            panelClass: 'success-snackbar',
          });
          this.getUserData();
        } else {
          this.snackBar.open(`${res.message}`, 'ERROR', {
            duration: 5000,
            panelClass: 'error-snackbar',
          });
        }
      });
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
