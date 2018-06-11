import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {User} from '../users/user';
import {mergeMap} from 'rxjs/operators';
import {UserInfo} from '../auth/auth.service';

@Injectable()
export class BackendService {
  private base = 'http://localhost:9090/backend/';
  private loginUrl = this.base + 'users/login';

  private http: HttpClient;
  private user: Observable<User>;

  constructor(http: HttpClient) {
    this.http = http;
  }

  private headers(): HttpHeaders {
    return new HttpHeaders()
      .set('Authorization', `Bearer ${localStorage.getItem('access_token')}`);
  }

  login(userInfoObservable: Observable<UserInfo>) {
    this.user = userInfoObservable
      .pipe(
        mergeMap((userInfo: UserInfo) => {
          return this.http.post<User>(this.loginUrl, userInfo, {
            headers: this.headers()
          });
        })
      );
  }

  getUser(): Observable<User> {
    return this.user;
  }
}
