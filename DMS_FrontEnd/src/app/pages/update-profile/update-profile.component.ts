import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {

  constructor(public login:LoginService) { }

  email=undefined;
  profileData={
    emailId:'',
    firstName:'',
    contactDetails:'',
    lastName:'',
    address:'',
    pincode:'',
    userId:''
  }
  
  ngOnInit(): void {
    this.email = this.login.getUser().emailId;
            if(this.login.getUserRole()=="ROLE_ADMIN")
            {
              this.login.getAdminProfile(this.email).subscribe(
                (data:any)=>{
                  this.profileData.firstName = data.firstName;
                  this.profileData.lastName = data.lastName;
                  this.profileData.emailId = data.emailId;
                  this.profileData.contactDetails = data.contactDetails;
                  this.profileData.address = data.address;
                  this.profileData.pincode = data.pincode;
                  this.profileData.userId = data.userId;
                },
                (error)=>{
                  console.log(error);
                }
              )
            }
            else if(this.login.getUserRole()=="ROLE_DISTRIBUTOR")
            {
              this.login.getDistributorProfile(this.email).subscribe(
                (data:any)=>{
                  this.profileData.firstName = data.firstName;
                  this.profileData.lastName = data.lastName;
                  this.profileData.emailId = data.emailId;
                  this.profileData.contactDetails = data.contactDetails;
                  this.profileData.address = data.address;
                  this.profileData.pincode = data.pincode;
                  this.profileData.userId = data.userId;
                },
                (error)=>{
                  console.log(error);
                }
              )
            }
            else if(this.login.getUserRole()=="ROLE_RETAILER")
            {
              this.login.getRetailerProfile(this.email).subscribe(
                (data:any)=>{
                  this.profileData.firstName = data.firstName;
                  this.profileData.lastName = data.lastName;
                  this.profileData.emailId = data.emailId;
                  this.profileData.contactDetails = data.contactDetails;
                  this.profileData.address = data.address;
                  this.profileData.pincode = data.pincode;
                  this.profileData.userId = data.userId;
                },
                (error)=>{
                  console.log(error);
                }
              )
            }
  }

  updateData(){

    if(this.login.getUserRole()=="ROLE_ADMIN")
            {
              this.login.updateAdminProfile(this.profileData).subscribe(
                (data)=>{
                  Swal.fire('Done','Data Updated Please Login Again','success').then((value) => {
                    this.login.logout();
                    setTimeout(function() {window.location.reload();},1000);
                  });
                },
                (error)=>{
                  console.log(error);
                  Swal.fire('Error','Error in Updating data','error');
                }
                )
            }
            else if(this.login.getUserRole()=="ROLE_DISTRIBUTOR")
            {
              this.login.updateDistributorProfile(this.profileData).subscribe(
                (data)=>{
                  Swal.fire('Done','Data Updated Please Login Again','success').then((value) => {
                    this.login.logout();
                    setTimeout(function() {window.location.reload();},1000);
                  });
                },
                (error)=>{
                  console.log(error);
                  Swal.fire('Error','Error in Updating data','error');
                }
                )
            }
            else if(this.login.getUserRole()=="ROLE_RETAILER")
            {
              this.login.updateRetailerProfile(this.profileData).subscribe(
                (data)=>{
                  Swal.fire('Done','Data Updated Please Login Again','success').then((value) => {
                    this.login.logout();
                    setTimeout(function() {window.location.reload();},1000);
                  });
                },
                (error)=>{
                  console.log(error);
                  Swal.fire('Error','Error in Updating data','error');
                }
                )
            }
  }
}
