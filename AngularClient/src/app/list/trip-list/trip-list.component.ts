import { Component, OnInit } from '@angular/core';
import {Trip} from '../../model/trip';
import {TripService} from '../../service/trip.service';
import {Customer} from '../../model/customer';
import {CustomerService} from '../../service/customer.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-trip-list',
  templateUrl: './trip-list.component.html',
  styleUrls: ['./trip-list.component.css']
})
export class TripListComponent implements OnInit {

  trips: Trip[];

  constructor(private tripService: TripService, private router: Router) {
  }

  ngOnInit() {
    this.tripService.findAll().subscribe(data => {
      this.trips = data;
    });
  }

  deleteModel(model: Trip) {
    this.tripService.delete(model).subscribe(() => {
      this.tripService.findAll().subscribe(data => {
        this.trips = data;
      });
    });
  }
}
