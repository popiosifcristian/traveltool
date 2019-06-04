import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Employee} from '../model/employee';

@Injectable()
export class EmployeeService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:1212/employee';
  }

  public findAll(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.url + '/findAll');
  }

  public save(employee: Employee) {
    return this.http.post<Employee>(this.url + '/save', employee);
  }

  public getOne(id: bigint): Observable<Employee> {
    return this.http.get<Employee>(this.url + '/getOne' + '?id=' + id);
  }

  public delete(employee: Employee) {
    return this.http.delete<Employee>(this.url + '/delete' + '?id=' + employee.id);
  }
}
