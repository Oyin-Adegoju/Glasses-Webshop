import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { OpslagruimteService } from 'src/app/Diensten/auth/authServices/opslagruimte/opslagruimte.service';

const BASIC_URL = environment['BASIC_URL'];

@Injectable({
  providedIn: 'root'
})
export class KlantService {

  constructor(private http: HttpClient) { }

  getProductsByTitle(title: any): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/customer/search/${title}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Products fetched successfully')),
      catchError(this.handleError<[]>('Error getting Products', []))
    );
  }

  getProductByCategoryId(categoryId: any): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/customer/products/${categoryId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Product fetched successfully')),
      catchError(this.handleError<[]>('Error getting product', []))
    );
  }

  getAllProducts(): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/customer/products`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Products fetched successfully')),
      catchError(this.handleError<[]>('Error getting Products', []))
    );
  }

  getAllCategories(): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/customer/categories`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Categories fetched successfully')),
      catchError(this.handleError<[]>('Error getting Categories', []))
    );
  }

  getCompleteProductDetailById(productId: number): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/customer/product/${productId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Product fetched successfully')),
      catchError(this.handleError<[]>('Error getting Product', []))
    );
  }

  // Cart Service

  getCartByUserId(): Observable<any> {
    const userId = OpslagruimteService.getUserId();
    return this.http.get<[]>(`${BASIC_URL}api/customer/cart/${userId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Cart fetched successfully')),
      catchError(this.handleError<[]>('Error getting Cart', []))
    );
  }

  addToCart(productId: any): Observable<any> {
    const cartDto = {
      productId: productId,
      userId: OpslagruimteService.getUserId(),
    };
    return this.http.post<[]>(`${BASIC_URL}api/customer/cart`, cartDto, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Product Added to Cart successfully')),
      catchError(this.handleError<[]>('Error adding Product to Cart', []))
    );
  }

  applyToken(code: any): Observable<any> {
    const userId = OpslagruimteService.getUserId();
    return this.http.get(`${BASIC_URL}api/customer/coupon/${userId}/${code}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  addMinusOnProduct(productId: any): Observable<any> {
    const quantityChangeProductDto = {
      userId: OpslagruimteService.getUserId(),
      productId: productId,
    };
    return this.http.post<[]>(`${BASIC_URL}api/customer/deduction`, quantityChangeProductDto, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Remove Product from Order successfully')),
      catchError(this.handleError<[]>('Error removing Product from Order', []))
    );
  }

  addPlusOnProduct(productId: any): Observable<any> {
    const quantityChangeProductDto = {
      userId: OpslagruimteService.getUserId(),
      productId: productId,
    };
    return this.http.post<[]>(`${BASIC_URL}api/customer/addition`, quantityChangeProductDto, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Added Product into Order successfully')),
      catchError(this.handleError<[]>('Error adding Product to Order', []))
    );
  }

  // Order Service

  // placeOrder(quantityChangeProductDto: any): Observable<any> {
  //   quantityChangeProductDto.userId = OpslagruimteService.getUserId();
  //   return this.http.post<[]>(`${BASIC_URL}api/customer/placeOrder`, quantityChangeProductDto, {
  //     headers: this.createAuthorizationHeader(),
  //   }).pipe(
  //     tap((_) => this.log('Order placed successfully')),
  //     catchError(this.handleError<[]>('Error placing Order', []))
  //   );
  // }

  placeOrder(orderDto: any): Observable<any> {
    orderDto.userId = OpslagruimteService.getUserId(); // Assuming you're attaching the userId here as well
    return this.http.post<[]>(`${BASIC_URL}api/order/createorder`, orderDto, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Order placed successfully')),
      catchError(this.handleError<[]>('Error placing Order', []))
    );
  }
  
  // getMyOrdersByUserId(): Observable<any> {
  //   const userId = OpslagruimteService.getUserId();
  //   return this.http.get<[]>(`${BASIC_URL}api/customer/myOrders/${userId}`, {
  //     headers: this.createAuthorizationHeader(),
  //   }).pipe(
  //     tap((_) => this.log('My Orders fetched successfully')),
  //     catchError(this.handleError<[]>('Error getting My Orders', []))
  //   );
  // }

  getMyOrdersByUserId(): Observable<any> {
    const userId = OpslagruimteService.getUserId();
    // Assuming the new API endpoint uses the userId as a path variable to fetch orders for that user
    return this.http.get<[]>(`${BASIC_URL}api/order/${userId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('My Orders fetched successfully')),
      catchError(this.handleError<[]>('Error getting My Orders', []))
    );
  }
  

  getOrderedProductsDetailsByOrderId(orderId: number): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/customer/ordered-products/${orderId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Products fetched successfully')),
      catchError(this.handleError<[]>('Error getting Products', []))
    );
  }

  // Reviews Operations

  giveReview(reviewDto: any): Observable<any> {
    console.log(reviewDto);
    return this.http.post<[]>(`${BASIC_URL}api/customer/review`, reviewDto, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Review posted successfully')),
      catchError(this.handleError<[]>('Error posting Review', []))
    );
  }

  // Wishlist Operation

  addProductToWishlist(wishlistDto: any): Observable<any> {
    return this.http.post<[]>(`${BASIC_URL}api/customer/wishlist`, wishlistDto, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Product added to wishlist successfully')),
      catchError(this.handleError<[]>('Error adding product to wishlist', []))
    );
  }

  getWishlistByUserId(): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/customer/wishlist/${OpslagruimteService.getUserId()}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Wishlist fetched successfully')),
      catchError(this.handleError<[]>('Error getting Wishlist', []))
    );
  }

  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set(
      'Authorization',
      'Bearer ' + OpslagruimteService.getToken()
    );
  }

  private log(message: string): void {
    console.log(`User Auth Service: ${message}`);
  }

  private handleError<T>(operation = 'operation', result?: T): any {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

     //remove product from cart  
  removeProductFromCart(userId: number, productId: number): Observable<any> {
    return this.http.delete(`${BASIC_URL}api/customer/cart/${userId}/${productId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Product removed from cart successfully')),
      catchError(this.handleError('Error removing product from cart'))
    );
  }



  removeProductFromWishlist(productId: number): Observable<any> {
    const userId = OpslagruimteService.getUserId(); // Assuming the user's ID is stored and retrieved like this
    return this.http.delete(`${BASIC_URL}api/customer/wishlist/${userId}/${productId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Product removed from wishlist successfully')),
      catchError(this.handleError('Error removing product from wishlist'))
    );
  }

  getProductsByPriceRange(minPrice: number, maxPrice: number): Observable<any> {
    const params = {
      minPrice: minPrice,
      maxPrice: maxPrice
    };
  
    return this.http.get<any[]>(`${BASIC_URL}api/customer/products`, {
      params: params,
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Products fetched successfully')),
      catchError(this.handleError<any[]>('Error getting Products', []))
    );
  }
  
  


}
  
  

