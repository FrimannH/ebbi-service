import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Customer} from '../dto/customer';
import { CustomerService } from '../service/customer.service';
import { ReportService } from '../service/report.service';

@Component({
  selector: 'admin-home',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private customerService: CustomerService,
              private reportService: ReportService) {}

  ngOnInit() {
    this.getCustomers();
  }

  private fileText;
  customerName: string = '';
  customerDescription: string = '';
  inputFileLabelValue = 'Select report file to import';
  customerId: number;
  customerId2: number;
  surveyUpdates: number;
  statusCode: number;
  customers: Customer[];
//  customer: Customer;

  getCustomers(): void {
    this.customerService.getCustomers().subscribe(customers => this.customers = customers);
  }

  addCustomer() {
    this.customerService.addCustomer(this.customerName, this.customerDescription).subscribe(customers => this.customers = customers);
  }

  deleteCustomer() {
    this.customerService.deleteCustomer(this.customerId).subscribe(customers => this.customers = customers);
  }

  uploadReportFile(event) {
    var reader = new FileReader();
    var adminComponent = this;

    reader.onload = function(e) {
      adminComponent.fileText = reader.result;
      var lines = adminComponent.fileText.split('\n');
      if ( lines.length > 0 && adminComponent.fileText == lines[0]) {
        lines = adminComponent.fileText.split('\r')
      }
      adminComponent.reportService.updateSurvey(adminComponent.customerId, lines).subscribe(surveyUpdates => {
        adminComponent.surveyUpdates = surveyUpdates} );
    }

    reader.readAsText(event.target.files[0]);

  }

  deleteSurvey() {
     this.reportService.deleteSurvey(this.customerId2).subscribe(statusCode => this.statusCode = statusCode);
  }

}
