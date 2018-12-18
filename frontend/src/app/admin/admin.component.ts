import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Customer} from '../dto/customer';
import { CustomerService } from '../service/customer.service';

@Component({
  selector: 'admin-home',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor() {}

  ngOnInit() {
  }

  //customers: Customer[];
  //
  //getHeroes(): void {
  //  this.customers = this.customerService.getCustomers();
  //}
}
