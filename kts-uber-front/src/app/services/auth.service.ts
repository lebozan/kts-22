import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) { }

  login(auth: any): Observable<any> {
    // console.log(auth);
    return this.http.post('api/auth/login', {email: auth.email, password: auth.password},
      {headers: this.headers, responseType: 'json'});
  }

  logout(): void {
    localStorage.clear();
  }

  register(auth: any): Observable<any> {
    return this.http.post('/api/passengers/register',
      {firstName: auth.firstName, lastName: auth.lastName, email: auth.email, password: auth.password},
      {headers: this.headers, responseType: 'json'});
  }

  isLoggedIn(): boolean {
    console.log(localStorage.getItem('user'));
    if (!localStorage.getItem('user')) {
      return false;
    }
    return true;
  }
}
