import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})

export class CreateUserComponent implements OnInit {
  public model;

  constructor(private http:HttpClient) {
    this.model={
      name:"",
      address:"",
      cuil:"",
      email:""
    };
  }

  ngOnInit() {
  }

  onSubmit() {
      this.http.post("http://localhost:9090/users/create", this.model, {})
      .subscribe((val) =>  {
        console.log("success")
      },
      response => {
        console.log("error");
        console.log(response)
      })
  }
}
