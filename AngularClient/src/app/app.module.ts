import { LandmarkService } from './service/landmark.service';
import { CustomerService } from './service/customer.service';
import { CompanyService } from './service/company.service';
import { BookingService } from './service/booking.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { BookingListComponent } from './list/booking-list/booking-list.component';
import { CompanyListComponent } from './list/company-list/company-list.component';
import { CustomerListComponent } from './list/customer-list/customer-list.component';
import { EmployeeListComponent } from './list/employee-list/employee-list.component';
import { LandmarkListComponent } from './list/landmark-list/landmark-list.component';
import { TripListComponent } from './list/trip-list/trip-list.component';
import { BookingFormComponent } from './form/booking-form/booking-form.component';
import { CompanyFormComponent } from './form/company-form/company-form.component';
import { CustomerFormComponent } from './form/customer-form/customer-form.component';
import { EmployeeFormComponent } from './form/employee-form/employee-form.component';
import { LandmarkFormComponent } from './form/landmark-form/landmark-form.component';
import { TripFormComponent } from './form/trip-form/trip-form.component';
import { EmployeeService } from './service/employee.service';
import { TripService } from './service/trip.service';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    BookingListComponent,
    CompanyListComponent,
    CustomerListComponent,
    EmployeeListComponent,
    LandmarkListComponent,
    TripListComponent,
    BookingFormComponent,
    CompanyFormComponent,
    CustomerFormComponent,
    EmployeeFormComponent,
    LandmarkFormComponent,
    TripFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    BookingService,
    CompanyService,
    CustomerService,
    EmployeeService,
    LandmarkService,
    TripService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
