  import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PublicationListComponent } from './publication-list/publication-list.component';
import { PublicationCreateComponent } from './publication-create/publication-create.component';
import { PublicationViewComponent } from './publication-view/publication-view.component';

const routes: Routes = [
  {path: 'publication', component: PublicationListComponent},
  {path: 'publication/create', component: PublicationCreateComponent},
  {path: 'publication/edit/:id', component: PublicationCreateComponent},
  {path: 'publication/:id', component: PublicationViewComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PublicationRoutingModule { }
