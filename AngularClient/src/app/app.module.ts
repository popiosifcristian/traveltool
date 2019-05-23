import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BookingListComponent } from './list/booking-list/booking-list.component';
import { CompanyListComponent } from './list/company-list/company-list.component';
import { CustomerListComponent } from './list/customer-list/customer-list.component';
import { EmployeeListComponent } from './list/employee-list/employee-list.component';
import { LandmarkListComponent } from './list/landmark-list/landmark-list.component';
import { TripListComponent } from './list/trip-list/trip-list.component';

@NgModule({
  declarations: [
    AppComponent,
    BookingListComponent,
    CompanyListComponent,
    CustomerListComponent,
    EmployeeListComponent,
    LandmarkListComponent,
    TripListComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
