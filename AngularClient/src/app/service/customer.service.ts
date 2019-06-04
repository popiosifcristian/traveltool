import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Customer} from '../model/customer';

@Injectable()
export class CustomerService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:1212/customer';
  }

  public findAll(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.url + '/findAll');
  }

  public save(customer: Customer) {
    return this.http.post<Customer>(this.url + '/save', customer);
  }

  public getOne(id: bigint): Observable<Customer> {
    return this.http.get<Customer>(this.url + '/getOne' + '?id=' + id);
  }

  public delete(customer: Customer) {
    return this.http.post<Customer>(this.url + '/delete', customer);
  }
}
