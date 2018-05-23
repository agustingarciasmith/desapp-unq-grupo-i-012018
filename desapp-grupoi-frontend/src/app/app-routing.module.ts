import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {UsersComponent} from './users/users.component';
import {CreateUserComponent} from './create-user/create-user.component';
import {UpdateUserComponent} from './update-user/update-user.component';
import {LoginComponent} from './login/login.component';

const appRoutes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: CreateUserComponent}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
