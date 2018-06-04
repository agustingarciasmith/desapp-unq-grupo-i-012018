import { Injectable } from '@angular/core';
import { Publication } from './publication';
import {HttpClient} from '@angular/common/http';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class PublicationService {

  private apiUrl = 'http://localhost:8080/publications';

  constructor(private http: Http) {
  }

  findAll(): Observable<any[]>  {
    return this.http.get(this.apiUrl)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  findById(id: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + id)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Error'));
  }

  savePublication(publication: Publication): Observable<Publication> {
    return this.http.post(this.apiUrl, publication)
    .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  deletePublicationById(id: number): Observable<boolean> {
    return this.http.delete(this.apiUrl + '/' + id)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  updatePublication(publication: Publication): Observable<Publication> {
    return null;
  }

  getCoordinates(address: any, city: any): Observable<Response> {
    const formattedAddress = (String(address) + ' ' + String(city)).replace(/\s+/g, '+');
    console.log(formattedAddress);
    // tslint:disable-next-line:max-line-length
    return this.http.get('https://maps.googleapis.com/maps/api/geocode/json?address=' + formattedAddress + 'Azcuenaga+600+buenos+aires&key=AIzaSyCJzUj8P1ub9CLKNcFiDI_i-ku5sqIyjF8');
  }

}
