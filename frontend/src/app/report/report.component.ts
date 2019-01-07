import { Component, OnInit } from '@angular/core';
import { Customer} from '../dto/customer';
import { CustomerService } from '../service/customer.service';
import { ReportService } from '../service/report.service';
import { GetReportResponse } from '../dto/GetReportResponse';
import { ReportItem } from '../dto/ReportItem';

import { ReportScore } from '../dto/ReportScore';
@Component({
  selector: 'report-home',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  constructor(private customerService: CustomerService,
              private reportService: ReportService) { }

  ngOnInit() {
    this.getReportCustomers();
    this.noDataDisplay = false;
    this.reportDataDisplay = true;
  }

  customerId: number;
  status: number
  noDataDisplay: boolean;
  reportDataDisplay: boolean;
  test = "test";
  reportCustomers: Customer[];
  getReportResponse: GetReportResponse;
  reportItems: ReportItem[];
  reportItem: ReportItem;

  getReportCustomers(): void {
    this.customerService.getCustomers().subscribe(customers => {
      this.reportCustomers = customers; });
  }

  getReport() {
    if (this.customerId != -1 && this.customerId !== undefined) {
      this.noDataDisplay = false;
      this.reportDataDisplay = true
      this.reportService.getReport(this.customerId).subscribe(getReportResponse => {
        this.getReportResponse = getReportResponse;
        //this.reportItems = this.getReportResponse.reportItems;
        //alert("Report item count: " + this.reportItems.length);
        //this.reportItem = this.reportItems[1];
        //alert("Question 2: " + this.reportItem.questionShortDescription);
        //this.test = this.reportItem.questionShortDescription;
        if (this.getReportResponse.statusCode != 0) {
          this.noDataDisplay = true
          this.reportDataDisplay = false;
        }
      });
    }
  }
}
