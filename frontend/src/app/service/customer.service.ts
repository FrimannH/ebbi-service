import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer} from '../dto/customer';
import { CUSTOMERS } from '../dto/mock-customers';

@Injectable()
export class CustomerService {

  constructor() {
  }

  //     private http: HttpClient
  //}

  getCustomers(): Customer[] {
    return CUSTOMERS;
  }
}

