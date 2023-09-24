import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  employee: any;

  constructor(
    private service: HttpService,
    private router: Router,
    private title: Title,
    private toaster: ToastrService
  ) { }

  ngOnInit(): void {

    this.getEmployee();
    this.title.setTitle("Profile")

  }

  getEmployee() {
    let employeeId = sessionStorage.getItem('EmployeeId');
    console.log(employeeId);
    if (employeeId) {
      this.service.getEmployeeById(employeeId).subscribe((data: any) => {
        this.employee = data;
      });
    }
  }
}


