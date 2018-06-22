import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PublicationRoutingModule } from './publication-routing.module';
import { PublicationListComponent } from './publication-list/publication-list.component';
import { PublicationCreateComponent } from './publication-create/publication-create.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GeocodingComponent } from '../components/geocoding/geocoding.component';
import { PublicationViewComponent } from './publication-view/publication-view.component';
import { AgmCoreModule } from '@agm/core';
import {CalendarModule} from 'primeng/calendar';
import {ChipsModule} from 'primeng/chips';
import {DropdownModule} from 'primeng/dropdown';
import { BarRatingModule } from 'ngx-bar-rating';
import { OrderModule } from 'ngx-order-pipe';

@NgModule({
  imports: [
    CommonModule,
    PublicationRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyD6fIyCESMmOPvPJ99SUgfWtOPrdhbPg1c',
    }),
    HttpModule,
    CalendarModule,
    ChipsModule,
    DropdownModule,
    BrowserAnimationsModule,
    BarRatingModule,
    OrderModule
  ],
  declarations: [PublicationListComponent, PublicationCreateComponent, PublicationViewComponent, GeocodingComponent]
})
export class PublicationModule { }
