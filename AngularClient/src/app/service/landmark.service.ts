import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Landmark} from '../model/landmark';

@Injectable()
export class LandmarkService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:1212/landmark';
  }

  public findAll(): Observable<Landmark[]> {
    return this.http.get<Landmark[]>(this.url + '/findAll');
  }

  public save(landmark: Landmark) {
    return this.http.post<Landmark>(this.url + '/save', landmark);
  }

  public getOne(id: bigint): Observable<Landmark> {
    return this.http.get<Landmark>(this.url + '/getOne' + '?id=' + id);
  }

  public delete(landmark: Landmark) {
    return this.http.delete<Landmark>(this.url + '/delete' + '?id=' + landmark.id);
  }
}
