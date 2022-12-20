import { Component, OnInit } from '@angular/core';
import { DistributordetailsService } from 'src/app/services/distributordetails.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-my-distributor',
  templateUrl: './my-distributor.component.html',
  styleUrls: ['./my-distributor.component.css']
})
export class MyDistributorComponent implements OnInit {

  distributorDetails=
    {
      emailId:'',
      firstName:'',
      contactDetails:'',
      lastName:'',
      address:'',
      pincode:'',
      enable:''
     };
  userId='';
  constructor(private distributor:DistributordetailsService, private login:LoginService) { }

  ngOnInit(): void {
    this.userId=this.login.getUser().userId;
    this.distributor.getDistributorOfRetailer(this.userId).subscribe(
      (data:any)=>{
        this.distributorDetails=data;
        console.log(this.distributorDetails)
      },
      (error)=>{
        console.log(error);
      }
    )
  }

}
