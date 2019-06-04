import { TripFormComponent } from './form/trip-form/trip-form.component';
import { LandmarkFormComponent } from './form/landmark-form/landmark-form.component';
import { EmployeeFormComponent } from './form/employee-form/employee-form.component';
import { CustomerFormComponent } from './form/customer-form/customer-form.component';
import { CompanyFormComponent } from './form/company-form/company-form.component';
import { BookingFormComponent } from './form/booking-form/booking-form.component';
import { LandmarkListComponent } from './list/landmark-list/landmark-list.component';
import { EmployeeListComponent } from './list/employee-list/employee-list.component';
import { CustomerListComponent } from './list/customer-list/customer-list.component';
import { CompanyListComponent } from './list/company-list/company-list.component';
import { BookingListComponent } from './list/booking-list/booking-list.component';
import { TripListComponent } from './list/trip-list/trip-list.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
    { path: 'bookings', component: BookingListComponent },
    { path: 'companies', component: CompanyListComponent },
    { path: 'customers', component: CustomerListComponent },
    { path: 'employees', component: EmployeeListComponent },
    { path: 'landmarks', component: LandmarkListComponent },
    { path: 'trips', component: TripListComponent },
    { path: 'addBooking', component: BookingFormComponent },
    { path: 'addCompany', component: CompanyFormComponent },
    { path: 'addCustomer', component: CustomerFormComponent },
    { path: 'addEmployee', component: EmployeeFormComponent },
    { path: 'addLandmark', component: LandmarkFormComponent },
    { path: 'addTrip', component: TripFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}