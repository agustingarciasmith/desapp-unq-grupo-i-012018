import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import {Vehicle} from './vehicle';
import {Observable} from 'rxjs/Observable';
import {User} from '../user';

@Injectable()
export class VehicleService {
  private http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  private headers(): HttpHeaders {
    return new HttpHeaders()
      .set('Authorization', `Bearer ${localStorage.getItem('access_token')}`);
  }

  addVehicleToUser(vehicle: Vehicle, id: number): Observable<Vehicle> {
      return this.http.post<Vehicle>('http://localhost:9090/backend/users/90/vehicles', vehicle, {
        headers: this.headers()
      });
  }
}

