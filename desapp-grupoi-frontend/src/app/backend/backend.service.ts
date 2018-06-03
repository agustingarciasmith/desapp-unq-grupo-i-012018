import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class BackendService {
  private base = 'http://localhost:9090/backend';
  private testAuth = this.base + '/holis';
  private loginUrl = this.base + '/login';

  private http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  private headers(): HttpHeaders {
    return new HttpHeaders()
      .set('Authorization', `Bearer ${localStorage.getItem('access_token')}`);
  }

  public test() {
    let a = this.http.get(this.testAuth, {
      headers: this.headers()
    });
    return a;
  }

  login(userInfo: Observable<Object>) {
    userInfo.subscribe(value => {
      this.http.post(this.loginUrl, value, {
          headers: this.headers()
        }
      ).subscribe(value1 => {
        console.log(value1);
      })
    })
  }
}
