import { Injectable } from '@angular/core';
import * as auth0 from 'auth0-js';
import { environment} from '../environments/environment';

@Injectable()
export class AuthServiceService {

  auth0 = new auth0.WebAuth({
    clientID: environment.authClientID,
    domain: environment.authDomain,
    responseType: environment.authResponseType,
    audience: environment.authAudience,
    redirectUri: environment.authRedirectUri,
    scope: environment.authScope
  });

  // constructor(public router: Router) {
  //
  // }

  public login(): void {
    this.auth0.authorize();
  }

}
