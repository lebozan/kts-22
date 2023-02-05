import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut() {
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
  }
  public saveUser(userid: string) {
    window.localStorage.removeItem(USER_KEY);
    window.localStorage.setItem(USER_KEY, userid);
  }

  public getToken() {
    return localStorage.getItem(TOKEN_KEY);
  }

  public getUser() {
    return localStorage.getItem(USER_KEY);
  }
}
