import { Component, OnInit } from '@angular/core';
import { DistributordetailsService } from 'src/app/services/distributordetails.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-get-distributor',
  templateUrl: './get-distributor.component.html',
  styleUrls: ['./get-distributor.component.css']
})
export class GetDistributorComponent implements OnInit {

  distributor=[
    {
      emailId:'',
      firstName:'',
      contactDetails:'',
      lastName:'',
      address:'',
      pincode:'',
      enable:''
     },
  ]

  constructor(private distributorService:DistributordetailsService) { }

  ngOnInit(): void {

    this.distributorService.getDistributor().subscribe(
      (data:any)=>{
        this.distributor=data;
        console.log(this.distributor);
      },
      (error)=>{
        Swal.fire('Error','Error in loading data from server','error');
      }
      )
  }

  disableDistributor(email:any)
  {
        Swal.fire({
          icon:'info',
          showCancelButton:true,
          confirmButtonText:'Disable',
          title: 'Are You Sure, You Want To Disable Distributor ?'
          }).then((result)=>{
            if(result.isConfirmed){
              this.distributorService.disableDistributor(email).subscribe(
                (data:any)=>{ 
                },
                (error)=>{
                  console.log(error);
                  Swal.fire('Success','Distributor Disabled','success').then((value) => {
                    setTimeout(function() {window.location.reload();},100);
                  });
                }
                )
            }
          });
  }

  enableDistributor(email:any)
  {
      Swal.fire({
        icon:'info',
        showCancelButton:true,
        confirmButtonText:'Enable',
        title: 'Are You Sure, You Want To Enable Distributor ?'
        }).then((result)=>{
          if(result.isConfirmed){
            this.distributorService.enableDistributor(email).subscribe(
              (data)=>{
              },
              (error)=>{
                console.log(error);
                Swal.fire('Success','Distributor Enabled','success').then((value) => {
                  setTimeout(function() {window.location.reload();},100);
                });
              }
              )
          }
        });
  }
}
