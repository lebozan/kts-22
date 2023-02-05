import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.form = this.fb.group({
      firstName : [null, Validators.required],
      lastName : [null, Validators.required],
      email : [null, Validators.required],
      password : [null, Validators.required],
      repeatPassword : [null, Validators.required],
      address : [null, Validators.required],
      phoneNumber : [null, Validators.required]
    });
  }

  ngOnInit(): void {
  }

  submit(): void {
    const auth: any = {};
    auth.firstName = this.form.value.firstName;
    auth.lastName = this.form.value.lastName;
    auth.email = this.form.value.email;
    auth.password = this.form.value.password;
    auth.repeatPassword = this.form.value.repeatPassword;
    if (auth.email === null || auth.password === null || auth.firstName === null || auth.lastName === null
      || auth.password !== auth.repeatPassword) {
      this.toastr.error('Repeated password and password don\'t match!');
      return;
    }
    this.authService.register(auth).subscribe(
      result => {
        console.log(result);
        // this.router.navigate([]);
        this.toastr.success('uspesna registracija');
      },
      error => {
        this.toastr.error(error.error);
      }
    );
  }

}
