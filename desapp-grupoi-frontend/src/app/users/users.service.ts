import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import {Observable} from 'rxjs/Observable';
import {User} from './user';

@Injectable()
export class UsersService {
  constructor(private http: HttpClient) {
  }

  private headers(): HttpHeaders {
    return new HttpHeaders()
      .set('Authorization', `Bearer ${localStorage.getItem('access_token')}`);
  }

  postUser(model: any) {
    this.http.post('http://localhost:9090/users/create', model, {})
      .subscribe((val) =>  {
          console.log(model);
          console.log('success');
        },
        response => {
          console.log(model);
          console.log('error');
          console.log(response);
        });
  }

  getUserById(id: number): Observable<any> {
    return this.http.get('http://localhost:9090/backend/users/' + id, {
      headers: this.headers()
    })
      .map(response => response)
      .catch(error => Observable.throw('Error in user service'));
  }

  updateUser(user: User): Observable<any> {
    return this.http.put('http://localhost:9090/backend/users/update/1', user)
      .map(response => response)
      .catch(error => Observable.throw('Error in user service'));
  }
}
