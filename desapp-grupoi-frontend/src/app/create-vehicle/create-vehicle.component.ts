import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {VehicleService} from '../vehicles/vehicleService';
import {Vehicle} from '../vehicles/vehicle';


@Component({
  selector: 'app-create-vehicle',
  templateUrl: './create-vehicle.component.html',
  styleUrls: ['./create-vehicle.component.css'],
  providers: [VehicleService]
})
export class CreateVehicleComponent implements OnInit {
  @Input() userId: number;
  dialogVehicle = false;
  pictures: string[];
  vehicle: Vehicle;
  newVehicle: Vehicle;

  constructor (private service: VehicleService) {
  }

  ngOnInit() {
    this.vehicle = Vehicle.emptyVehicle();
    this.newVehicle = Vehicle.emptyVehicle();
  }

  toggleAddVehicleDialog() {
    this.dialogVehicle = !this.dialogVehicle;
  }

  addPicture(picture: string) {
    if (this.pictures.length < 6) {
      this.pictures.push(picture);
    }
  }

  addVehicleToUser() {
    this.service.addVehicleToUser(this.newVehicle, this.userId).subscribe(data => {alert("Succesfully Added Product details")},Error => {alert("failed while adding product details")});
    this.toggleAddVehicleDialog();
  }

  addVehicle() {
    this.newVehicle = this.vehicle.copy();
    this.toggleAddVehicleDialog();
  }
}
