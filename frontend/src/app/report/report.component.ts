import { Component, OnInit } from '@angular/core';
import { Customer} from '../dto/customer';
import { CustomerService } from '../service/customer.service';
import { ReportService } from '../service/report.service';
import { GetReportResponse } from '../dto/GetReportResponse';

@Component({
  selector: 'report-home',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  constructor(private customerService: CustomerService,
              private reportService: ReportService) { }

  ngOnInit() {
    this.getCustomers();
    this.noDataDisplay = false;
    this.reportDataDisplay = true;
  }

  customerId: number;
  status: number
  noDataDisplay: boolean;
  reportDataDisplay: boolean;
  customers: Customer[];
  getReportResponse: GetReportResponse;

  getCustomers(): void {
    this.customerService.getCustomers().subscribe(customers => this.customers = customers);
  }

  getReport() {
    this.noDataDisplay = false;
    this.reportDataDisplay = true
    this.reportService.getReport(this.customerId).subscribe(getReportResponse => {
       this.getReportResponse = getReportResponse;
       if ( this.getReportResponse.statusCode == 0) {
         this.noDataDisplay = true
         this.reportDataDisplay = false;
       }
     });

  }
}
