import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {AuthComponent} from './auth/auth.component';
import {GuardService} from './auth/guard.service';
import {PublicationListComponent} from './publication/publication-list/publication-list.component';
import {WelcomeComponent} from './welcome/welcome.component';
import {paths} from "./paths";

const appRoutes: Routes = [
  {path: '', redirectTo: paths.welcome, pathMatch: 'full'},
  {path: paths.welcome, component: WelcomeComponent},
  {path: paths.login, component: LoginComponent},
  {path: paths.auth, component: AuthComponent},
  {path: paths.home, component: HomeComponent, canActivate: [GuardService]},
  {path: paths.publication, component: PublicationListComponent, canActivate: [GuardService]},
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
