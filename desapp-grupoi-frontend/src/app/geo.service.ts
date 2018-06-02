import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
@Injectable()
export class GeoService {
    constructor(private http: Http) { }
    getLocation(term: string): Promise<any> {
        return this.http.get('http://maps.google.com/maps/api/geocode/json?address=' + term.replace(/ /g, '+') + '&sensor=false')
             .toPromise()
             .then((response) => Promise.resolve(response.json()));
     }

     setLocation(lat: number, lng: number): Promise<any> {
        return this.http.get('https://maps.googleapis.com/maps/api/geocode/json?latlng='
          + lat + ',' + lng + '&key=AIzaSyD6fIyCESMmOPvPJ99SUgfWtOPrdhbPg1c')
        .toPromise()
        .then((response) => Promise.resolve(response.json()));
     }
}
