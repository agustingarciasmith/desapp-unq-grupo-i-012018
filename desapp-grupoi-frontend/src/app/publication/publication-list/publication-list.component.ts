import { Component, OnInit } from '@angular/core';
import { PublicationService } from '../publication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-publication-list',
  templateUrl: './publication-list.component.html',
  styleUrls: ['./publication-list.component.css'],
  providers: [PublicationService]
})
export class PublicationListComponent implements OnInit {

  public publications: any[];

  constructor(private router: Router,
    private publicationService: PublicationService) { }

  ngOnInit() { // when component loading get all publications and set the publications[]
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
}
