import { ReportItem } from './ReportItem';

export class GetReportResponse {
  statusCode: number;
  message: String;
  customerName: string;
  customerId; string;
  reportItems: ReportItem[];

  constructor(statusCode, message, customerName,  customerId) {
    this.statusCode = statusCode;
    this.message = message;
    this.customerName = customerName;
    this.customerId = customerId;
  }
}
