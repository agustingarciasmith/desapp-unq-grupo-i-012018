import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {CreateUserComponent} from './create-user/create-user.component';
import {LoginComponent} from './login/login.component';
import {AppComponent} from './app.component';
import {AuthComponent} from './auth/auth.component';
import {GuardService} from './auth/guard.service';
import {PublicationListComponent} from './publication/publication-list/publication-list.component';

const appRoutes: Routes = [
  {path: '', component: AppComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: CreateUserComponent},
  {path: 'auth', component: AuthComponent},
  {path: 'home', component: HomeComponent, canActivate: [GuardService]},
  {path: 'publication', component: PublicationListComponent, canActivate: [GuardService]}
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
