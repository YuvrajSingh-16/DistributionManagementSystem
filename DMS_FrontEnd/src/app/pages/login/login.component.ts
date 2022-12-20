import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData={
    emailId:'',
    password:'',
  }
  constructor(private snack:MatSnackBar, private login:LoginService,private router:Router) { }

  ngOnInit(): void {
  }

  reload(){
    window.location.reload();
  }

  formSubmit()
  {
    if(this.loginData.emailId.trim()=='' || this.loginData.emailId.trim()==null)
    {
      this.snack.open('EmailID is required', 'ok', { duration:3000})
      return
    }
    if(this.loginData.password.trim()=='' || this.loginData.password.trim()==null)
    {
      this.snack.open('Password is required', 'ok', { duration:3000})
      return;
    }

    //request to server to generate token
    this.login.generateToken(this.loginData).subscribe(
      (data: any)=>{
        console.log("Success");
        console.log(data);
        
        //login
        this.login.loginUser(data.token);
        this.login.getCurrentUser().subscribe(
          (user: any)=>{
            this.login.setUser(user);
            console.log(user);
            //redirect...ADMIN: admin-dashboard
            //redirect...NORMAL: normal-dashboard

            if(this.login.getUserRole()=="ROLE_ADMIN")
            {
              //admin-dashboard
              //window.location.href='/admin-dashboard';
              this.router.navigate(['admin-dashboard']);
              this.login.loginStatusSubject.next(true);
              console.log("inside getUserRole")
            }
            else if(this.login.getUserRole()=="ROLE_DISTRIBUTOR")
            {
              //distributor-dashboard
              //window.location.href='/distributor-dashboard';
              this.router.navigate(['distributor-dashboard']);
              this.login.loginStatusSubject.next(true);
            }
            else if(this.login.getUserRole()=="ROLE_RETAILER")
            {
              //retailer-dashboard
              //window.location.href='/retailer-dashboard';
              this.router.navigate(['retailer-dashboard']);
              this.login.loginStatusSubject.next(true);
            }
            else
            {
              this.login.logout();
            }
          }
        );
      },
      (error:any)=>{
        console.log("Error !");
        console.log(error);
        this.snack.open("Invalid Details !! Try again",'',{duration:3000});
      }
      );
  } 
}
