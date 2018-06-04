import { Component, Input, Output, EventEmitter } from '@angular/core';
import { GeoService } from '../../geo.service';

@Component({
  selector: 'app-geocoding',
  moduleId: module.id,
  templateUrl: `./geocoding.component.html`,
  styleUrls: ['./geocoding.component.css'],
  providers: [GeoService]
})
export class GeocodingComponent {
  @Input() name: string;
  @Output() address: EventEmitter<string> = new EventEmitter<string>();
  lat = -34.6022056;
  lng = -58.4443721;
  zoom = 15;
  location: any;
  label = "A";

  findLocation(): void {
    this.geoService.getLocation(this.location)
      .then((response) => (this.lat = response.results[0].geometry.location.lat, this.lng = response.results[0].geometry.location.lng))
      .then(() => (this.notifyAddress()))
      .catch((error) => console.error(error));
  }

  setNewLocation(): void {
    this.geoService.setLocation(this.lat, this.lng)
      .then((response) => (this.location = response.results[0].formatted_address))
      .then(() => (this.notifyAddress()))
      .catch((error) => console.error(error));
  }

  notifyAddress(): void {
    this.address.emit(this.location);
  }
  constructor(private geoService: GeoService) {
  }
  clickedMarker(label: string, index: number) {
  }
  mapClicked($event: MouseEvent) {
  }
  markerDragEnd($event: MouseEvent) {
    var coords: any = $event;
    this.lat = coords.coords.lat;
    this.lng = coords.coords.lng;
    this.setNewLocation();
  }
}
// tslint:disable-next-line:class-name
interface marker {
  lat: number;
  lng: number;
  label?: string;
  draggable: boolean;
}
