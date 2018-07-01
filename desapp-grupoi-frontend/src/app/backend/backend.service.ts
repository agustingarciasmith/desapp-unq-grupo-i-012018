import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {map, mergeMap} from 'rxjs/operators';
import {UserInfo} from '../auth/auth.service';
import {User} from '../user';
import {environment} from '../../environments/environment';
import {Vehicle} from '../vehicles/vehicle';
import {of} from 'rxjs/observable/of';
import {Publication} from "../publication/publication";
import {Reservation} from '../reservation';

@Injectable()
export class BackendService {
  private base = environment.backendUrl;
  private loginUrl = this.base + 'users/login';
  private updateUserUrl = this.base + 'users/update';
  private addVehicleUrl = this.base + 'vehicles/';
  private publicationsUrl = this.base + "publication";
  private createPublicationUrl = this.base + "publication/create";
  private userPublicationsUrl = this.base + "publication/user/";
  private createReservationUrl = this.base + 'reservation/create';

  private http: HttpClient;
  public user$: Observable<User>;
  private userSubscriptions: Array<{ next, error }>;
  private publications$: Observable<Publication[]>;
  private publicationsSubscriptions: Array<{ next, error }>;

  constructor(http: HttpClient) {
    this.http = http;
    this.user$ = of(User.emptyUser());
    this.publications$ = of([]);
    this.userSubscriptions = [];
    this.publicationsSubscriptions = [];
  }

  private headers(): HttpHeaders {
    return new HttpHeaders()
      .set('Authorization', `Bearer ${localStorage.getItem('access_token')}`);
  }

  login(userInfoObservable: Observable<UserInfo>) {
    this.user$ = userInfoObservable
      .pipe(
        mergeMap((userInfo: UserInfo) => {
          return this.http.post<User>(this.loginUrl, userInfo, {
            headers: this.headers()
          });
        })
      );

    this.publications$ = this.user$.pipe(
      mergeMap((user: User) => {
        return this.http.get<Publication[]>(this.userPublicationsUrl + user.id, {
          headers: this.headers()
        })
      })
    );

    this.notifyUserSubscriptors();
    this.notifyPublicationsSubscriptors();
  }

  subscribeToPublications(next, error?) {
    this.publicationsSubscriptions.push({next, error});
    this.notifyPublicationsSubscriptors();
  }

  subscribeToUser(next, error?) {
    this.userSubscriptions.push({next, error});
    this.notifyUserSubscriptors();
  }

  updateUser(user: User): Observable<User> {
    this.user$ = this.http.put<User>(this.updateUserUrl, user, {
      headers: this.headers()
    });

    this.notifyUserSubscriptors();
    return this.user$;
  }

  addVehicleToUser(newVehicle: Vehicle) {
    this.user$ = this.user$.pipe(
      mergeMap((user: User) => {
        return this.http.post<Vehicle>(this.addVehicleUrl + user.id, newVehicle, {
          headers: this.headers()
        }).pipe(
          map(
            (vehicle: Vehicle) => {
              const realUser = User.from(user);
              realUser.addVehicle(vehicle);
              return realUser;
            }
          )
        );
      })
    );
    this.notifyUserSubscriptors();
  }

  deleteVehicleFromUser(vehicleToDelete: Vehicle) {
    const httpOptions = {
      headers: this.headers(), body: vehicleToDelete
    };
    this.user$ = this.user$.pipe(
      mergeMap((user: User) => {
        return this.http.delete<Vehicle>(this.addVehicleUrl + user.id, httpOptions
        ).pipe(
          map(
            () => {
              const realUser = User.from(user);
              realUser.removeVehicle(vehicleToDelete);
              return realUser;
            }
          )
        );
      })
    );
    this.notifyUserSubscriptors();
  }

  private notifyUserSubscriptors() {
    this.userSubscriptions.map(subsciption => {
      this.user$.subscribe(subsciption.next, subsciption.error);
      this.user$.subscribe(value => console.log(value));
    });
  }

  getPublications(): Observable<Publication[]> {
    return this.http.get<Publication[]>(this.publicationsUrl, {
      headers: this.headers()
    });
  }

  addPublicationToUser(publication: Publication) {
    this.publications$ =
      this.user$.pipe(
        mergeMap((user: User) => {
          publication.owner = user;

          return this.http.post<Publication>(this.createPublicationUrl, publication, {
              headers: this.headers()
            }
          )
        })
      ).pipe(
        mergeMap((publication2: Publication) => {
          return this.publications$.map((publications: Publication[]) => {
            publications.push(publication2);
            return publications;
          })
        })
      );
  }

  private notifyPublicationsSubscriptors() {
    this.publicationsSubscriptions.map(subsciption => {
      this.publications$.subscribe(subsciption.next, subsciption.error)
    });
  }

  submitReservation(reservation: Reservation): any {
    console.log(reservation);
    return this.http.post<Reservation>(this.createReservationUrl, reservation, {
      headers: this.headers()
    }).map(res => console.log(res));
  }
}
