import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { OrderService } from 'src/app/services/order.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-more-order-details',
  templateUrl: './more-order-details.component.html',
  styleUrls: ['./more-order-details.component.css']
})
export class MoreOrderDetailsComponent implements OnInit {

  public retailerDetails={
    emailId:'',
    firstName:'',
    contactDetails:'',
    lastName:'',
    password:'',
    address:'',
    pincode:'',
  }
  public distributorDetails={
    emailId:'',
    firstName:'',
    contactDetails:'',
    lastName:'',
    password:'',
    address:'',
    pincode:'',
  }
  userId='';

  constructor(private route:ActivatedRoute, private login:LoginService) { }

  ngOnInit(): void {
    this.userId=this.route.snapshot.params['userId'];
    
    this.login.findUser(this.userId).subscribe(
      (data:any)=>{
        this.retailerDetails=data;
        console.log(this.retailerDetails);
      },
      (error)=>{
        Swal.fire('Error','Something Went Wrong','error');
      }
    )

    this.login.getDistributorOfRetailer(this.userId).subscribe(
      (data:any)=>{
        this.distributorDetails=data;
        console.log(this.distributorDetails)
      },
      (error)=>{
        Swal.fire('Error','Something Went Wrong','error');
      }
    )
  }

}
