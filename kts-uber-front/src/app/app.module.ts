import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from "@angular/common/http";
import {JwtModule} from "@auth0/angular-jwt";
import {MaterialModule} from "./material/material.module";
import { RegisterComponent } from './components/register/register.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ToastrModule} from "ngx-toastr";
import { HomeUserComponent } from './components/core/home-user/home-user.component';
import { MainAppComponent } from './components/core/main-app/main-app.component';
import { NavbarAppComponent } from './components/core/navbar-app/navbar-app.component';
import { ToolbarAppComponent } from './components/core/toolbar-app/toolbar-app.component';
import { UnregisteredPageComponent } from './components/core/unregistered-page/unregistered-page.component';

export function tokenGetter() {
  return localStorage.getItem("auth-token");
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeUserComponent,
    MainAppComponent,
    NavbarAppComponent,
    ToolbarAppComponent,
    UnregisteredPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter
      }
    }),
    MaterialModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
