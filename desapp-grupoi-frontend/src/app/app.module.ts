import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AgmCoreModule} from '@agm/core';
import {HomeComponent} from './home/home.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {UpdateUserComponent} from './update-user/update-user.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToasterModule} from 'angular5-toaster/dist';
import {LoginComponent} from './login/login.component';
import {AuthService} from './auth/auth.service';
import {AuthComponent} from './auth/auth.component';
import {GuardService} from './auth/guard.service';
import {BackendService} from './backend/backend.service';
import {CalendarModule} from 'primeng/calendar';
import {DialogModule} from 'primeng/dialog';
import {FileUploadModule} from 'primeng/fileupload';
import {MyProfileComponent} from './components/my-profile/my-profile.component';
import {PublicationModule} from './publication/publication.module';
import {WelcomeComponent} from './welcome/welcome.component';
import {MomentModule} from "angular2-moment";
import { ClientReservationComponent } from './client-reservation/client-reservation.component';
import { BarRatingModule } from 'ngx-bar-rating';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UpdateUserComponent,
    LoginComponent,
    AuthComponent,
    MyProfileComponent,
    WelcomeComponent,
    ClientReservationComponent,
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
    ReactiveFormsModule,
    BrowserAnimationsModule,
    ToasterModule,
    CalendarModule,
    DialogModule,
    FileUploadModule,
    PublicationModule,
    MomentModule,
    BarRatingModule,
  ],
  providers: [
    AuthService,
    GuardService,
    BackendService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
