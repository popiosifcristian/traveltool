import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Trip} from '../model/trip';

@Injectable()
export class TripService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:1212/trip';
  }

  public findAll(): Observable<Trip[]> {
    return this.http.get<Trip[]>(this.url + '/findAll');
  }

  public save(trip: Trip) {
    return this.http.post<Trip>(this.url + '/save', trip);
  }

  public getOne(id: bigint): Observable<Trip> {
    return this.http.get<Trip>(this.url + '/getOne' + '?id=' + id);
  }

  public delete(trip: Trip) {
    return this.http.delete<Trip>(this.url + '/delete' + '?id=' + trip.id);
  }
}
