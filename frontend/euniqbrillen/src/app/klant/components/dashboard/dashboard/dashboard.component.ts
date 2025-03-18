import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { KlantService } from 'src/app/klant/service/klant.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  products: any[] = [];
  categories: { name: string, checked: boolean }[] = []; // Array to store categories with checkbox state
  filteredProducts: any[] = []; // Array to store filtered products
  searchProductForm!: FormGroup;
  isSpinning = false;
  zoomedImage: string | null = null; // Track the magnified image

  

  constructor(
    private klantService: KlantService,
    private snackBar: MatSnackBar,
    private fb: FormBuilder,
   
  ) { }

  ngOnInit(): void {
    this.searchProductForm = this.fb.group({
      title: [null, [Validators.required]],
    });
    this.getAllProducts();
    this.getAllCategories(); // Fetch categories when component initializes
    this.categories.forEach(category => category.checked = true);
  
  // Initially, display all products
  this.filteredProducts = [...this.products];
  }


  submitForm(): void {
    this.isSpinning = true;
    const title = this.searchProductForm.get('title')!.value;
    this.klantService.getProductsByTitle(title).subscribe((res) => {
      this.searchFilteredProducts = res.map(element => ({
        ...element,
        processedImg: 'data:image/jpeg;base64,' + element.returnedImg
      }));
      // Apply category filters to search results
      this.applyFilters();
      this.isSpinning = false;
    });
  }



  getAllProducts(): void {
    this.isSpinning = true;
    this.klantService.getAllProducts().subscribe((res) => {
      this.products = res.map(element => ({
        ...element,
        processedImg: 'data:image/jpeg;base64,' + element.returnedImg
      }));
      this.isSpinning = false;
    });
  }

  getAllCategories(): void {
    this.klantService.getAllCategories().subscribe((res) => {
      // Initialize checked property to true for each category
      this.categories = res.map(category => ({ name: category.name, checked: true }));
      // Apply initial category filter
      this.applyCategoryFilter();
    });
  }

  applyCategoryFilter(): void {
    this.applyFilters();

  }
  applyFilters(): void {
    // If there's a search applied, use searchFilteredProducts as base, otherwise use all products
    let baseProducts = this.searchFilteredProducts.length > 0 ? this.searchFilteredProducts : this.products;
    const selectedCategories = this.categories.filter(category => category.checked).map(category => category.name);
    if (selectedCategories.length > 0) {
      this.filteredProducts = baseProducts.filter(product => selectedCategories.includes(product.categoryName));
    } else {
      this.filteredProducts = [...baseProducts];
    }
  }

  addToCart(productId: any) {
    this.klantService.addToCart(productId).subscribe((res) => {
      console.log(res);
      if (res.id != null) {
        this.snackBar.open("Artikel is aan winkelmand toegevoegd", "Close", { duration: 5000 })
      } else if (res.id == null) {
        this.snackBar.open("Dit product zit al in de winkelmand", "Close", { duration: 5000 })
      }
    });
  }



  setMagnifiedImage(imageUrl: string): void {
    this.zoomedImage = imageUrl;
  }

  // Method to clear magnified image URL
  clearMagnifiedImage(): void {
    this.zoomedImage = null;
  }

  searchFilteredProducts: any[] = [];

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
