import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class BackendService {
  private base = 'http://localhost:9090';
  private testAuth = this.base + '/holis';

  private http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  private headers(): HttpHeaders {
    return new HttpHeaders()
      .set('Authorization', `Bearer ${localStorage.getItem('access_token')}`)
  }

  public test() {
    let a = this.http.get(this.testAuth, {
      headers: this.headers()
    });
    return a
  }
}
