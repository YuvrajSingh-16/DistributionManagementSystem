import { Component, OnInit } from '@angular/core';
import { GetRetailerService } from 'src/app/services/get-retailer.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-retailer-details',
  templateUrl: './retailer-details.component.html',
  styleUrls: ['./retailer-details.component.css']
})
export class RetailerDetailsComponent implements OnInit {

  retailer:any=[
    {
      emailId:'',
      firstName:'',
      contactDetails:'',
      lastName:'',
      address:'',
      pincode:'',
      enable:''
     },
  ];

  constructor(private retailerService:GetRetailerService) { }

  ngOnInit(): void {
    this.retailerService.retailerByAdmin().subscribe(
      (data:any)=>{
        this.retailer=data;
        console.log(this.retailer);
      },
      (error)=>{
        Swal.fire('Sorry','Something Went Wrong','error').then((value) => {
          window.location.href='/admin-dashboard/get-distributor';
        });
      }
    )
  }

  disableRetailer(email:any)
  {
        Swal.fire({
          icon:'info',
          showCancelButton:true,
          confirmButtonText:'Disable',
          title: 'Are You Sure, You Want To Disable Retailer ?'
          }).then((result)=>{
            if(result.isConfirmed){
              this.retailerService.disableRetailer(email).subscribe(
                (data:any)=>{ 
                },
                (error)=>{
                  console.log(error);
                  Swal.fire('Done',' Retailer Disabled','success').then((value) => {
                    setTimeout(function() {window.location.reload();},100);
                  });
                }
                )
            }
          });
  }

  enableRetailer(email:any)
  {
      Swal.fire({
        icon:'info',
        showCancelButton:true,
        confirmButtonText:'Enable',
        title: 'Are You Sure, You Want To Enable Retailer ?'
        }).then((result)=>{
          if(result.isConfirmed){
            this.retailerService.enableRetailer(email).subscribe(
              (data)=>{
              },
              (error)=>{
                console.log(error);
                Swal.fire('Done','Retailer Enabled','success').then((value) => {
                  setTimeout(function() {window.location.reload();},100);
                });
              }
              )
          }
        });
  }

}
