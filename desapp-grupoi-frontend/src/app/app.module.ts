import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {PublicationModule} from './publication/publication.module';
import {AgmCoreModule} from '@agm/core';
import {UsersComponent} from './users/users.component';
import {HomeComponent} from './home/home.component';
import {CreateUserComponent} from './create-user/create-user.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {UpdateUserComponent} from './update-user/update-user.component';
import {CalendarModule} from 'primeng/calendar';
import { CreateVehicleComponent } from './create-vehicle/create-vehicle.component';
import {DialogModule} from 'primeng/dialog';
import {FileUploadModule} from 'primeng/fileupload';
import { StarRatingModule } from 'levon-angular-star-rating';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UsersComponent,
    CreateUserComponent,
    UpdateUserComponent,
    CreateVehicleComponent,
  ],
  imports: [
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyD6fIyCESMmOPvPJ99SUgfWtOPrdhbPg1c',
    }),
    CalendarModule,
    BrowserModule,
    AppRoutingModule,
    PublicationModule,
    HttpClientModule,
    FormsModule,
    CalendarModule,
    DialogModule,
    FileUploadModule,
    StarRatingModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
}
