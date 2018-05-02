import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {PublicationService} from '../publication.service';
import {Publication} from '../publication';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-publication-create',
  templateUrl: './publication-create.component.html',
  styleUrls: ['./publication-create.component.css'],
  providers: [PublicationService]
})

export class PublicationCreateComponent implements OnInit, OnDestroy {

  id: number;
  publication: Publication;

  publicationForm: FormGroup;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private publicationService: PublicationService) { }

  ngOnInit() {

    this.publicationForm = new FormGroup({
      city: new FormControl('', Validators.required),
      pickUpAddress: new FormControl('', Validators.required),
      returnAddress: new FormControl('', Validators.required),
      contactPhone: new FormControl('', Validators.required),
      cost: new FormControl('', Validators.required)
    });

  }

  ngOnDestroy(): void {
  }

  onSubmit() {
    if (this.publicationForm.valid) {

        // tslint:disable-next-line:prefer-const
        let publication: Publication = new Publication(null,
        this.publicationForm.controls['city'].value,
        this.publicationForm.controls['pickUpAddress'].value,
        this.publicationForm.controls['returnAddress'].value,
        this.publicationForm.controls['contactPhone'].value,
        this.publicationForm.controls['cost'].value);
        this.publicationService.savePublication(publication).subscribe();

     }
      this.publicationForm.reset();
      this.router.navigate(['/publication']);
  }

  redirectPublicationPage() {
    this.router.navigate(['/publication']);

  }
}
