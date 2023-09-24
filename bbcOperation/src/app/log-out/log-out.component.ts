import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Title } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-log-out',
  templateUrl: './log-out.component.html',
  styleUrls: ['./log-out.component.css']
})
export class LogOutComponent implements OnInit {

  constructor(  
    private toaster: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  cancelLogout() {
    this.router.navigate(['/home']);
  }

  confirmLogout() {
    sessionStorage.removeItem("EmployeeId");
    sessionStorage.clear();

    this.toaster.success("Logout SuccessFul")
    this.router.navigate(['/login']);
  }
}
