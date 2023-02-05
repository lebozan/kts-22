import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {TokenStorageService} from "../../services/token-storage.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";


export interface TokenResponse {
  accessToken: string;
  expiresIn: string;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.form = this.fb.group({
      email : [null, Validators.required],
      password : [null, Validators.required]

    });
  }

  ngOnInit(): void {
  }

  submit(): void {
    const auth: any = {};
    auth.email = this.form.value.email;
    auth.password = this.form.value.password;

    this.authService.login(auth).subscribe(
      result => {
        const token: any = result;
        localStorage.setItem('user', token.accessToken);
        localStorage.setItem('expiresIn', token.expiresIn);
        this.router.navigate(['/']);
        location.reload();
      },
      error => {
        this.toastr.error(error.error);
      }
    );
  }
}
