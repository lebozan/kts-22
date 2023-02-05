import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {LoginGuard} from "./guards/login.guard";
import {RegisterComponent} from "./components/register/register.component";
import {UnregisteredPageComponent} from "./components/core/unregistered-page/unregistered-page.component";

const routes: Routes = [
  {
    path: 'login', component : LoginComponent, canActivate: [LoginGuard]
  }, {
    path: 'register', component : RegisterComponent, canActivate: [LoginGuard]
  },
  {
    path: 'unregistered-main', component: UnregisteredPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
