import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PublicationRoutingModule } from './publication-routing.module';
import { PublicationListComponent } from './publication-list/publication-list.component';
import { PublicationCreateComponent } from './publication-create/publication-create.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PublicationViewComponent } from './publication-view/publication-view.component';
import { AgmCoreModule } from '@agm/core';

@NgModule({
  imports: [
    CommonModule,
    PublicationRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    AgmCoreModule
  ],
  declarations: [PublicationListComponent, PublicationCreateComponent, PublicationViewComponent]
})
export class PublicationModule { }
