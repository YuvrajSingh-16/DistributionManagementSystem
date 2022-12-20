import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { OrderService } from 'src/app/services/order.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-manage-orders',
  templateUrl: './manage-orders.component.html',
  styleUrls: ['./manage-orders.component.css']
})
export class ManageOrdersComponent implements OnInit {
  pincode=0;
  viewOrders: any=null;
  

  constructor(private manageOrders:OrderService, public login:LoginService) { }

  ngOnInit(): void {
    this.pincode=this.login.getUser().pincode;
    this.manageOrders.viewOrders(this.pincode).subscribe((data: any)=>
    {
      this.viewOrders=data;
      console.log(this.viewOrders);
      
    },
    (error: any)=>{
      console.log(error);
      Swal.fire('Error!!','Error loading data from server','error');
  })
  }
  
public orderApproval(){
  
    this.manageOrders.approveOrder(this.viewOrders[0].orderId, this.viewOrders[0].categoryId).subscribe(
      (data)=>{
        Swal.fire('Done!','Order Has Been Approved','success').then((value) => {
          setTimeout(function() {window.location.reload();},100);
        });
      },
      (error)=>{
        Swal.fire('Error','Something Went Wrong','error');
      }
      )
}
}
