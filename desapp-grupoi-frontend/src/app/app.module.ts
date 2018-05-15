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

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UsersComponent,
    CreateUserComponent,
    UpdateUserComponent
  ],
  imports: [
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCJzUj8P1ub9CLKNcFiDI_i-ku5sqIyjF8',
    }),
    BrowserModule,
    AppRoutingModule,
    PublicationModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
}
