import { Component, OnInit } from '@angular/core';
import {Booking} from '../../model/booking';
import {BookingService} from '../../service/booking.service';

@Component({
  selector: 'app-booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css']
})
export class BookingListComponent implements OnInit {

  bookings: Booking[];

  constructor(private bookingService: BookingService) {
  }

  ngOnInit() {
    this.bookingService.findAll().subscribe(data => {
      this.bookings = data;
    });
  }

}
