import {TripService} from './../../service/trip.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Booking} from 'src/app/model/booking';
import {BookingService} from 'src/app/service/booking.service';
import {Trip} from 'src/app/model/trip';
import {trigger} from '@angular/animations';

@Component({
  selector: 'app-booking-form',
  templateUrl: './booking-form.component.html',
  styleUrls: ['./booking-form.component.css']
})
export class BookingFormComponent implements OnInit {
  booking: Booking;
  tripId: bigint;
  trip: Trip;

  constructor(private route: ActivatedRoute, private router: Router, private bookingService: BookingService,
              private tripService: TripService) {
    this.booking = new Booking();
  }

  ngOnInit() {
  }

  async onSubmit() {
    this.booking.id = 0;
    // this.trip = await this.tripService.getOne(this.tripId).toPromise();
    // this.booking.trip = this.trip;
    // this.bookingService.save(this.booking).subscribe(() => this.goToBookingList());
  
    this.tripService.getOne(this.tripId).subscribe((res) => {
      this.booking.trip = res;
      this.bookingService.save(this.booking).subscribe(() => this.goToBookingList());
    });
  }

  goToBookingList() {
    this.router.navigate(['/bookings']);
  }

}
