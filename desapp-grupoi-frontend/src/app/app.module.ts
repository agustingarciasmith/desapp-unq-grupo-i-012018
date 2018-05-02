import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PublicationModule } from './publication/publication.module';
import { HttpModule } from '@angular/http';
import { AgmCoreModule } from '@agm/core';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCJzUj8P1ub9CLKNcFiDI_i-ku5sqIyjF8',
    }),
    BrowserModule,
    AppRoutingModule,
    PublicationModule,
    HttpModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
