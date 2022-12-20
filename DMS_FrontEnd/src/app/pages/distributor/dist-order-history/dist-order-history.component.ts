import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { DistOrderHistoryService } from 'src/app/services/dist-order-history.service';
import { GetRetailerService } from 'src/app/services/get-retailer.service';
import { LoginService } from 'src/app/services/login.service';
import Swal from 'sweetalert2';
import {AfterViewInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';


@Component({
  selector: 'app-dist-order-history',
  templateUrl: './dist-order-history.component.html',
  styleUrls: ['./dist-order-history.component.css']
})
export class DistOrderHistoryComponent implements OnInit {
  
  pincode=0;
  user:any={};
  orderHistory: any=[
    {
      orderId:'',
      date:'',
      orderStatus:'',
      totalAmount: '',
      quantity: '',
      userId: '',
      categoryId: '',
      orderProductCategories: [
          {
              id: '',
              productCategories: {
                  categoryId: '',
                  categoryName: '',
                  price: '',
                  products: {
                      productId: '',
                      productName: '',
                  }
              }
          }
      ]
  },
  ];
  

  constructor(private _showOrders:DistOrderHistoryService, public login:LoginService, private retailer:GetRetailerService) { }

  ngOnInit(): void {
    this.pincode=this.login.getUser().pincode;
    this._showOrders.orderHistory(this.pincode).subscribe((data: any)=>
    {
      this.orderHistory=data;
      console.log(this.orderHistory);
      
    },
    (error: any)=>{
      console.log(error);
      Swal.fire('Error!!','Error loading data from server','error');
  })

  this.retailer.findUser(this.orderHistory.userId).subscribe(
    (data:any)=>{
      this.user=data;
      console.log(this.user)
    },
    (error)=>{
      console.log(error);
    }
    )
}

public userName(){
 
}

  displayedColumns: string[] = ['orderId','categoryName','productName','price','quantity','totalAmount','orderStatus','retailerId'];
  dataSource = this.orderHistory;

  // @ViewChild(MatPaginator) paginator:MatPaginator;

  // ngAfterViewInit() {
  //   this.orderHistory.paginator = this.paginator;
  // }
}
