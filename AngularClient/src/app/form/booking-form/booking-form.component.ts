import { TripService } from './../../service/trip.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Booking } from 'src/app/model/booking';
import { BookingService } from 'src/app/service/booking.service';
import { Trip } from 'src/app/model/trip';

@Component({
  selector: 'app-booking-form',
  templateUrl: './booking-form.component.html',
  styleUrls: ['./booking-form.component.css']
})
export class BookingFormComponent implements OnInit {
  booking: Booking;
  tripId: bigint;
  constructor(private route: ActivatedRoute, private router: Router, private bookingService: BookingService,
              private tripService: TripService) {
    this.booking = new Booking();
   }

   ngOnInit() {}

  onSubmit() {
    this.tripService.getOne(this.tripId).subscribe(data => {
      this.booking.trip = data;
    });
    this.bookingService.save(this.booking).subscribe(result => this.goToBookingList());
  }

  goToBookingList() {
    this.router.navigate(['/bookings']);
  }

}
