import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, map, of, tap, throwError} from 'rxjs';
import { environment } from'src/environments/environment.development';
import { OpslagruimteService } from '../opslagruimte/opslagruimte.service';




const BASIC_URL = environment['BASIC_URL'];
export const AUTH_HEADER = 'authorization';


@Injectable({
  providedIn: 'root'
})

export class AuthService {
  constructor(
    private http: HttpClient,
    private opslagruimteservice: OpslagruimteService
  ) { }

  login(username: string, password: string): any {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = { username, password };

    return this.http.post(BASIC_URL + 'authenticate', body, { headers, observe: 'response' }).pipe(
      map((res) => {
        const token = res.headers.get(AUTH_HEADER)?.substring(7);
        const user = res.body;
        if (token && user) {
          this.opslagruimteservice.saveToken(token);
          this.opslagruimteservice.saveUser(user);
          return true;
        }
        return false;
      }),
      catchError((error) => {
        console.error('Login Error:', error);
        return throwError(error);
      })
    );
  }

  register(signupRequest: any): Observable<any> {
    return this.http.post(BASIC_URL + "/sign-up", signupRequest);
  }

  getUserById(): Observable<any> {
    const userId = OpslagruimteService.getUserId();
    return this.http.get<[]>(`${BASIC_URL}api/user/${userId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('User Fetched successfully')),
      catchError(this.handleError<[]>('Error Fetching User', []))
    );
  }

  updateUser(data): Observable<any> {
    return this.http.post<[]>(`${BASIC_URL}api/update`, data, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('User Updated successfully')),
      catchError(this.handleError<[]>('Error Updating User', []))
    );
  }

  updatePassword(changePasswordDto: any): Observable<any> {
    changePasswordDto.id = OpslagruimteService.getUserId();
    return this.http.post<[]>(`${BASIC_URL}api/updatePassword`, changePasswordDto, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Password Updated successfully')),
      catchError(this.handleError<[]>('Error Updating Password', []))
    );
  }

  // Track Order

  getOrderByTrackingId(trackingId: number): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}order/${trackingId}`)
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
  
}


