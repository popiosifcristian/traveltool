import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Booking} from '../model/booking';

@Injectable()
export class BookingService {
  private bookingUrl: string;

  constructor(private http: HttpClient) {
    this.bookingUrl = 'http://localhost:1212/booking';
  }

  public findAll(): Observable<Booking[]> {
    return this.http.get<Booking[]>(this.bookingUrl);
  }

  public save(booking: Booking) {
    return this.http.post<Booking>(this.bookingUrl, booking);
  }

  public getOne(id: bigint): Observable<Booking> {
    return this.http.get<Booking>(this.bookingUrl + '?id=' + id);
  }

  public delete(booking: Booking) {
    return this.http.post<Booking>(this.bookingUrl, booking);
  }
}
