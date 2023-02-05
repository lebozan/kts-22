import { Component, OnInit } from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";

@Component({
  selector: 'app-main-app',
  templateUrl: './main-app.component.html',
  styleUrls: ['./main-app.component.scss']
})
export class MainAppComponent implements OnInit {

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

  refresh(event: any): void {
    location.reload();
  }
}
