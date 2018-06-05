import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AgmCoreModule} from '@agm/core';
import {HomeComponent} from './home/home.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {UpdateUserComponent} from './update-user/update-user.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToasterModule} from 'angular5-toaster/dist';
import {LoginComponent} from './login/login.component';
import {AuthService} from './auth/auth.service';
import {AuthComponent} from './auth/auth.component';
import {GuardService} from './auth/guard.service';
import {BackendService} from './backend/backend.service';
import {CalendarModule} from 'primeng/calendar';
import {CreateVehicleComponent} from './create-vehicle/create-vehicle.component';
import {DialogModule} from 'primeng/dialog';
import {FileUploadModule} from 'primeng/fileupload';
import {StarRatingModule} from 'levon-angular-star-rating';
import {MyProfileComponent} from './components/my-profile/my-profile.component';
import {PublicationModule} from './publication/publication.module';
import {WelcomeComponent} from './welcome/welcome.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UpdateUserComponent,
    LoginComponent,
    AuthComponent,
    CreateVehicleComponent,
    MyProfileComponent,
    WelcomeComponent
  ],
  imports: [
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyD6fIyCESMmOPvPJ99SUgfWtOPrdhbPg1c',
    }),
    CalendarModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    ToasterModule,
    CalendarModule,
    DialogModule,
    FileUploadModule,
    StarRatingModule,
    PublicationModule
  ],
  providers: [
    AuthService,
    GuardService,
    BackendService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
