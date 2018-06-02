import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { PublicationService } from '../publication.service';
import { Publication } from '../publication';
import { ActivatedRoute, Router } from '@angular/router';
import { SelectItem } from 'primeng/api';


@Component({
  selector: 'app-publication-create',
  templateUrl: './publication-create.component.html',
  styleUrls: ['./publication-create.component.css'],
  providers: [PublicationService],
})

export class PublicationCreateComponent implements OnInit, OnDestroy {

  id: number;
  availableDates: [string];
  publication: Publication;
  pickUpAddress: string;
  returnAddres: string;
  vehicles: SelectItem[];
  selectedVehicle: string;
  publicationForm: FormGroup;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private publicationService: PublicationService) {


    this.vehicles = [
      { label: 'Select Vehicle', value: null },
      { label: 'Ford Ranger 2010 DDD111', value: '1' },
      { label: 'VW Gol 2015 FFF111', value: '2' },
      { label: 'Chevrolet Corsa 2017 GGG111', value: '3' },
    ];
  }

  ngOnInit() {

    this.publicationForm = new FormGroup({
      contactPhone: new FormControl('', Validators.required),
      cost: new FormControl('', Validators.required)
    });

  }

  ngOnDestroy(): void {
  }

  onNotifyPickUpAddress(pickUpAddress: string): void {
    this.pickUpAddress = pickUpAddress;
  }

  onNotifyReturnAddress(returnAddress: string): void {
    this.returnAddres = returnAddress;
  }

  onSubmit() {
    if (this.publicationForm.valid) {

      this.publicationService.savePublication(null).subscribe();
    }
    this.publicationForm.reset();
    this.router.navigate(['/publication']);
  }

  redirectPublicationPage() {
    this.router.navigate(['/publication']);
  }
}
