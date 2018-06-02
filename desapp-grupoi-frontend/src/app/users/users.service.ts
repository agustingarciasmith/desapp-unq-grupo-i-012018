import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
@Injectable()
export class UsersService {
  constructor(private http: HttpClient) {
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
}
