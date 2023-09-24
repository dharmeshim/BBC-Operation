import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  username: string | null = '';

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.username = sessionStorage.getItem("EmployeeId");
  }

  redirectToHome() {
    this.router.navigate(['/add-customer']);
  }

  onRedirectToAllCustomers() {
    this.router.navigate(['/all-customers']);
  }
  onToUploadCustomerCsv() {
    this.router.navigate(['/customer-csv-upload']);
  }

  redirectToAddBill() {
    this.router.navigate(['/add-bill']);
  }

  redirectToAddBillCsv() {
    this.router.navigate(["/bill-csv-upload"])
  }

  redirectToPayBill() {
    this.router.navigate(["/mark-bill-pay"])
  }

  redirectToTrasactions() {
    this.router.navigate(["/list-all-trasactions"])
  }

  redirectToDetails() {
    this.router.navigate(["/profile-details"])
  }

  redirectToCustomerTrasactions() {
    this.router.navigate(["/customer-transactions"])
  }

  redirectToLogOut() {
    this.router.navigate(["/log-out"])
  }
}
