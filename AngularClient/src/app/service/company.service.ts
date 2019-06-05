import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Company} from '../model/company';

@Injectable()
export class CompanyService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:1212/company';
  }

  public findAll(): Observable<Company[]> {
    return this.http.get<Company[]>(this.url + '/findAll');
  }

  public save(company: Company) {
    return this.http.post<Company>(this.url + '/save', company);
  }

  public getOne(id: bigint): Observable<Company> {
    return this.http.get<Company>(this.url + '/getOne' + '?id=' + id);
  }

  public delete(company: Company) {
    return this.http.delete<Company>(this.url + '/delete' + '?id=' + company.id);
  }
}
