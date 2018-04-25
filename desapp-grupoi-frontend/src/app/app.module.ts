import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AuthServiceService} from './auth-service.service';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AgmCoreModule} from '@agm/core';
import {UsersComponent} from './users/users.component';
import {HomeComponent} from './home/home.component';
import {CreateUserComponent} from './create-user/create-user.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {UpdateUserComponent} from './update-user/update-user.component';
import {LoginComponent} from './login/login.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToasterModule} from 'angular5-toaster/dist';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UsersComponent,
    CreateUserComponent,
    UpdateUserComponent,
    LoginComponent
  ],
  imports: [
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCJzUj8P1ub9CLKNcFiDI_i-ku5sqIyjF8',
    }),
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    ToasterModule
  ],
  providers: [AuthServiceService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
