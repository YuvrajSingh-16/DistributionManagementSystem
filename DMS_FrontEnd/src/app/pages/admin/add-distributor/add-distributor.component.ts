import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DistributordetailsService } from 'src/app/services/distributordetails.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-distributor',
  templateUrl: './add-distributor.component.html',
  styleUrls: ['./add-distributor.component.css']
})
export class AddDistributorComponent implements OnInit {

  constructor(private snack:MatSnackBar, private distributorService:DistributordetailsService) { }

  public distributor={
    emailId:'',
    firstName:'',
    contactDetails:'',
    lastName:'',
    password:'',
    address:'',
    pincode:'',
  }

  ngOnInit(): void {
  }

  distributorSubmit()
    {
      if(this.distributor.emailId=='' || this.distributor.emailId==null)
    {
      this.snack.open("Email is Required..!!", 'Ok' , { duration:3000});
      return;
    }
      if(this.distributor.password=='' || this.distributor.password==null)
    {
      this.snack.open("Password is Required..!!", 'Ok' , { duration:3000});
      return;
    }
    if(this.distributor.firstName=='' || this.distributor.firstName==null)
    {
      this.snack.open("Firstname is Required..!!", 'Ok' , { duration:3000});
      return;
    }
    if(this.distributor.lastName=='' || this.distributor.lastName==null)
    {
      this.snack.open("Lastname is Required..!!", 'Ok' , { duration:3000});
      return;
    }
    if(this.distributor.contactDetails=='' || this.distributor.contactDetails==null)
    {
      this.snack.open("Phone number is Required..!!", 'Ok' , { duration:3000});
      return;
    }
    if(this.distributor.address=='' || this.distributor.address==null)
    {
      this.snack.open("Address is Required..!!", 'Ok' , { duration:3000});
      return;
    }
    if(this.distributor.pincode=='' || this.distributor.pincode==null)
    {
      this.snack.open("Pincode is Required..!!", 'Ok' , { duration:3000});
      return;
    }
   

    //add Distributor
    this.distributorService.addDistributor(this.distributor).subscribe(
      (data:any)=>{
        console.log(data);
        this.distributor=data;
        console.log(this.distributor);
        Swal.fire('Success','Distributor is successfully Added','success').then((value) => {
            window.location.href='/admin-dashboard/get-distributor'
          });
      },
      (error)=>{
        console.log(error);
        if(error.status==500){
          this.snack.open('User Already Exist','ok',{duration:3000});
        }
        
      }
    )

    }

}
