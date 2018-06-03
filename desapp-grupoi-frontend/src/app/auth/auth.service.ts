import {Injectable} from '@angular/core';
import * as auth0 from 'auth0-js';
import {Router} from '@angular/router';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthService {

  auth0 = new auth0.WebAuth({
    clientID: environment.authClientID,
    domain: environment.authDomain,
    responseType: environment.authResponseType,
    audience: environment.authAudience,
    redirectUri: environment.authRedirectUri,
    scope: environment.authScope,
  });
  public userInfo: Observable<Object>;

  constructor(public router: Router, public http: HttpClient) {
  }

  public login(): void {
    this.auth0.authorize();
  }

  public handleAuthentication(): void {
    this.auth0.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        this.setSession(authResult);
        this.router.navigate(['/home']);
      } else if (err) {
        this.router.navigate(['/login']);
        console.log(err);
        alert(`Error: ${err.error}. Check the console for further details.`);
      }
    });
  }

  private setSession(authResult): void {
    // Set the time that the access token will expire at
    const expiresAt = JSON.stringify((authResult.expiresIn * 1000) + new Date().getTime());
    localStorage.setItem('access_token', authResult.accessToken);
    localStorage.setItem('id_token', authResult.idToken);
    localStorage.setItem('expires_at', expiresAt);
  }

  public logout(): void {
    // Remove tokens and expiry time from localStorage
    localStorage.removeItem('access_token');
    localStorage.removeItem('id_token');
    localStorage.removeItem('expires_at');
    // Go back to the home route
    this.router.navigate(['/']);
  }

  public isAuthenticated(): boolean {
    // Check whether the current time is past the
    // access token's expiry time
    const expiresAt = JSON.parse(localStorage.getItem('expires_at') || '{}');
    return new Date().getTime() < expiresAt;
  }

  public getUserInfo(): Observable<Object> {
    const accessToken = localStorage.getItem('access_token');

    if (!accessToken) {
      throw new Error('Access Token must exist to fetch profile');
    }

    if(this.userInfo) {
      return this.userInfo;
    } else {
      this.userInfo = this.http.get("https://unq-desa-grupoi.auth0.com/userinfo", {
        headers: new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('access_token')}`)
      });
      return this.userInfo;
    }
  }
}
