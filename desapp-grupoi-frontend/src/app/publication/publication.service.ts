import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class PublicationService {

  constructor(private http: HttpClient) {
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
