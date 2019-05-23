import { Component, OnInit } from '@angular/core';
import {Trip} from '../../model/trip';
import {TripService} from '../../service/trip.service';

@Component({
  selector: 'app-trip-list',
  templateUrl: './trip-list.component.html',
  styleUrls: ['./trip-list.component.css']
})
export class TripListComponent implements OnInit {

  trips: Trip[];

  constructor(private tripService: TripService) {
  }

  ngOnInit() {
    this.tripService.findAll().subscribe(data => {
      this.trips = data;
    });
  }
}
