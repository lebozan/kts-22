import { Component, OnInit } from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";

@Component({
  selector: 'app-navbar-app',
  templateUrl: './navbar-app.component.html',
  styleUrls: ['./navbar-app.component.scss']
})
export class NavbarAppComponent implements OnInit {
  currentUser: any;

  constructor() {
    this.currentUser = {};
  }

  ngOnInit(): void {
    const token = localStorage.getItem('user');
    const jwt: JwtHelperService = new JwtHelperService();
    if (!token) {
      return;
    }
    const info = jwt.decodeToken(token);
    this.currentUser = info;
  }

}
