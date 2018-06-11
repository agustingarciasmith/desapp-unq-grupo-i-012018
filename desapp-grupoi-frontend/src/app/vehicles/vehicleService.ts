import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import {Vehicle} from './vehicle';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class VehicleService {
  constructor(private http: HttpClient) {
  }

  private headers(): HttpHeaders {
    return new HttpHeaders()
      .set('Authorization', `Bearer ${localStorage.getItem('access_token')}`);
  }

  createAndAddVehicleToUser(id: number, vehicle: Vehicle): Observable<any> {
      return this.http.put('http://localhost:9090/backend/users/' + id + '/vehicles', vehicle, {
        headers: this.headers()
      })
        .map(response => console.log(response))
        .catch(error => Observable.throw('Error in user service'));
  }
}

