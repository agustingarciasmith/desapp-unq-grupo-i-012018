import {Component, OnInit} from '@angular/core';
import {PublicationService} from '../publication.service';
import {Router} from '@angular/router';
import {OrderPipe} from 'ngx-order-pipe';

@Component({
  selector: 'app-publication-list',
  templateUrl: './publication-list.component.html',
  styleUrls: ['./publication-list.component.css'],
  providers: [PublicationService]
})
export class PublicationListComponent implements OnInit {
  order = 'owner.totalScore';
  reverse = false;
  public publications: any[];
  public sortedPublications: any[];
  public p = 1;

  constructor(private router: Router, private publicationService: PublicationService, private orderPipe: OrderPipe) {
    this.sortedPublications = orderPipe.transform(this.publications, 'owner.totalScore');
  }

  ngOnInit() {
    this.getAllPublications();
  }

  getAllPublications() {
    this.publicationService.findAll().subscribe(
      publications => {
        this.publications = publications;
      },
      err => {
        console.log(err);
      }
    );
  }

  redirectNewPublicationPage() {
    this.router.navigate(['/publication/create']);
  }

  viewPublicationPage(publication: any) {
    if (publication) {
      console.log(publication.id);
      this.router.navigate(['/publication', publication.id]);
    }
  }

  deletePublication(publication: any) {
    if (publication) {
      this.publicationService.deletePublicationById(publication.id).subscribe(
        res => {
          this.getAllPublications();
          this.router.navigate(['/publication']);
          console.log('done');
        }
      );
    }
  }

  setOrder(value: string) {
    if (this.order === value) {
      this.reverse = !this.reverse;
    }
    this.order = value;
  }
}
