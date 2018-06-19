import { Injectable } from '@angular/core';
import { Publication } from './publication';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class PublicationService {

  private apiUrl = 'http://localhost:9090/publication';

  constructor(private http: HttpClient) {
  }

  private headers(): HttpHeaders {
    return new HttpHeaders()
      .set('Authorization', `Bearer ${localStorage.getItem('access_token')}`);
  }

  findAll(): Observable<Publication[]>  {
    return this.http.get(this.apiUrl, {
      headers: this.headers()
    })
      .map((res: Response) => res)
      .catch((error: any) => Observable.throw(error.error || 'Server error'));
  }

  findById(id: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + id , {
      headers: this.headers()
    })
      .map((res: Response) => res)
      .catch((error: any) => Observable.throw(error.error || 'Error'));
  }

  savePublication(publication: Publication) {
    return this.http.post<Publication>(this.apiUrl+ '/create', publication, {
      headers: this.headers()
    }).map(res => console.log(res));
  }

  deletePublicationById(id: number): Observable<boolean> {
    return this.http.delete(this.apiUrl + '/' + id)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  updatePublication(publication: Publication): Observable<Publication> {
    return null;
  }

  getCoordinates(address: string): Observable<any> {
    const formattedAddress = address.replace(/\s+/g, '+');
    console.log(formattedAddress);
    // tslint:disable-next-line:max-line-length
    return this.http.get('https://maps.googleapis.com/maps/api/geocode/json?address=' + formattedAddress + '&key=AIzaSyCJzUj8P1ub9CLKNcFiDI_i-ku5sqIyjF8')
      .map((res: Response) => res)
      .catch((error: any) => Observable.throw(error.error || 'Server error'));
  }

}
