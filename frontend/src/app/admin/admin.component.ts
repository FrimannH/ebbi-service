import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Customer} from '../dto/customer';
import { CustomerService } from '../service/customer.service';
import { ReportService } from '../service/report.service';
import { UpdateSurveyResponse } from '../dto/UpdateSurveyResponse';
import { DeleteSurveyResponse } from '../dto/DeleteSurveyResponse';

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
    this.deletedSurvey = false;
    this.updatedSurvey = false;
  }

  private fileText;
  customerName: string = '';
  customerDescription: string = '';
  surveyStartDate: string = '';
  surveyEndDate: string = '';
  surveyAgeFrom: string = "";
  surveyAgeTo: string = "";
  customerId1: number;
  customerId2: number;
  customerId3: number;
  deletedSurvey: boolean;
  updatedSurvey: boolean;
  updatedSurveyCount: number
  deletedSurveyCount: number
  inputFileLabelValue = 'Select survey file to upload';
  updateSurveyResponse: UpdateSurveyResponse;
  deleteSurveyResponse: DeleteSurveyResponse;
  statusCode: number;
  customers: Customer[];


  getCustomers(): void {
    //alert("get admin customers")
    this.customerService.getCustomers().subscribe(customers => {
      this.customers = customers;
    });
  }

  addCustomer() {
    this.deletedSurvey = false;
    this.updatedSurvey = false;
    this.customerService.addCustomer(this.customerName, this.customerDescription).subscribe(customers => this.customers = customers);
  }

  deleteCustomer() {
    if (this.customerId1 != -1 && this.customerId1 !== undefined) {
      this.deletedSurvey = false;
      this.updatedSurvey = false;
      this.customerService.deleteCustomer(this.customerId1).subscribe(customers => this.customers = customers);
    }
  }

  uploadReportFile(event) {
    if (this.customerId2 != -1 && this.customerId2 !== undefined) {
      this.deletedSurvey = false;
      this.updatedSurvey = false;
      var reader = new FileReader();
      var adminComponent = this;

      reader.onload = function (e) {
        adminComponent.fileText = reader.result;
        var lines = adminComponent.fileText.split('\n');
        if (lines.length > 0 && adminComponent.fileText == lines[0]) {
          lines = adminComponent.fileText.split('\r')
        }
        adminComponent.reportService.updateSurvey(adminComponent.customerId2, lines, adminComponent.surveyStartDate, adminComponent.surveyEndDate, adminComponent.surveyAgeFrom, adminComponent.surveyAgeTo).subscribe(updateSurveyResponse => {
          adminComponent.updateSurveyResponse = updateSurveyResponse
          if (adminComponent.updateSurveyResponse.statusCode == 0) {
            adminComponent.updatedSurveyCount = adminComponent.updateSurveyResponse.rowsAffected;
            adminComponent.updatedSurvey = true;
          } else {
            alert(adminComponent.updateSurveyResponse.message + "  " + adminComponent.updateSurveyResponse.statusCode)
          }

        });
      }

      reader.readAsText(event.target.files[0]);

    }
  }

  deleteSurvey() {
    if (this.customerId3 != -1 && this.customerId3 !== undefined) {
      this.deletedSurvey = false;
      this.reportService.deleteSurvey(this.customerId3).subscribe(deleteSurveyResponse => {
        this.deleteSurveyResponse = deleteSurveyResponse
        if (this.deleteSurveyResponse.statusCode == 0) {
          this.deletedSurveyCount = this.deleteSurveyResponse.rowsAffected;
          this.deletedSurvey = true;
        } else {
          alert(this.deleteSurveyResponse.message + "   " + this.deleteSurveyResponse.statusCode)
        }
      });
    }
  }

}
