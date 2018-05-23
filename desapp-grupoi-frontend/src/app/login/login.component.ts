import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ToasterService} from 'angular5-toaster/dist';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private _http: HttpClient;
  private model: { email: string };
  private _toasterService: ToasterService;
  private _router: Router;

  constructor(http: HttpClient, toasterService: ToasterService, router:Router) {
    this._http = http;
    this._toasterService = toasterService;
    this._router = router;
    this.model = {
      email: ''
    };
  }

  ngOnInit() {
  }

  onSubmit() {
    this._http.post('http://localhost:9090/login', this.model, {})
      .subscribe((val) => {
          console.log('success');
          this._router.navigate(['/userHome']);
        },
        response => {
          console.log('error');
          console.log(response);
          this._toasterService.pop('error', 'Login fallido', 'El e-mail ingresado no existe')
        });
  }

}
