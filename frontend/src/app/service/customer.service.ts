import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer} from '../dto/customer';
import { CustomersResponse} from '../dto/CustomersResponse';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private httpClient: HttpClient) {
  }

  private baseURL = 'http://localhost:9091';

  getCustomers(): Observable<Customer[]> {
    return this.httpClient.get<CustomersResponse>(this.baseURL + '/customers')
           .pipe(map(response => response.customers)
  )};

  addCustomer(customerName: string, customerDescription: string): Observable<Customer[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    let body: string = "{\"customerName\":" + "\"" + customerName + "\", \"customerDescription\": " + "\"" + customerDescription+ "\"}";
    return this.httpClient.post<CustomersResponse>(this.baseURL + '/customer', body, httpOptions)
      .pipe(map(response => response.customers)
  )};

  deleteCustomer(customerId: number): Observable<Customer[]> {
    return this.httpClient.delete<CustomersResponse>(this.baseURL + '/customer/' + customerId)
      .pipe(map(response => response.customers)
    )};

}

