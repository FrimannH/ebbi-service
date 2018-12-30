import { Customer} from './customer';

export class CustomersResponse {
  customers: Customer[];
  statusCode: number;
  message: String;

  constructor(customers, statusCode, message) {
    this.customers = customers;
    this.statusCode = statusCode;
    this.message = message;
  }
}
