import {Component, OnInit} from '@angular/core';
import {Booking} from '../../model/booking';
import {BookingService} from '../../service/booking.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css']
})
export class BookingListComponent implements OnInit {

  bookings: Booking[];

  constructor(private bookingService: BookingService, private router: Router) {
  }

  ngOnInit() {
    this.bookingService.findAll().subscribe(data => {
      this.bookings = data;
    });
  }

  deleteModel(booking: Booking) {
    this.bookingService.delete(booking).subscribe(() => {
      console.log('it\'s done');
      this.bookingService.findAll().subscribe(data => {
        this.bookings = data;
      });
    });
  }
}
