import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../user';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})

export class CreateUserComponent implements OnInit {
  public model;

  constructor(private http: HttpClient) {
    this.model = User.emptyUser();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.http.post('http://localhost:9090/users/create', this.model, {})
      .subscribe((val) => {
          this.model = val;
          console.log("success")
        },
        response => {
          console.log('error');
          console.log(response);
        });
  }
}
