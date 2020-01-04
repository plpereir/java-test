import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = '/api/v1/customer/';

  constructor(private http: HttpClient, private route:ActivatedRoute,private router:Router) { }

  getCustomer(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  updateCustomer(id: string, value: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  createCustomer(customer: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, customer);
  }

  getCustomersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  deleteCustomer(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  withdrawmoneyCustomer(id: string, value: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/cash/${id}/${value}`, { responseType: 'text' });
  }
}
