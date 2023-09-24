import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-login-otp',
  templateUrl: './login-otp.component.html',
  styleUrls: ['./login-otp.component.css']
})
export class LoginOtpComponent implements OnInit {

  otpGenerated: boolean = false;

  constructor(private service: HttpService,
    private toaster: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    document.body.className = 'bg_background';

  }

  onSubmit(id: number, otp: string) {
    let obj = {
      employeeId: id,
      otp: otp
    };

    this.service.verifyOtp(obj)
      .subscribe((response: any) => {

        if (response.isValid) {
          sessionStorage.setItem("EmployeeId", response.employee.employeeId);
          console.log(response.employee.employeeId);
          this.router.navigate(['/home']);
        } else {
          this.toaster.error("Incorrect OTP!")
        }
      },
        (error) => {
          console.error('incorrect OTP:', error);
          this.toaster.error("Incorrect OTP!")
        });
  }


  generateOTP(id: number) {

    this.service.sendOtp(id)
      .subscribe(
        (response: any) => {
          if (response == 'Employee Not Exist') {
            this.toaster.error("Employee Not Exist")
          }
          else {
            console.log(response);
            this.toaster.success("Otp Sent")
            this.otpGenerated = true;
            alert("displaying otp for test: " + response + "\nemployee can find in email")
          }
        }
      );
  }



  ngOnDestroy(): void {
    document.body.className = '';
  }

}