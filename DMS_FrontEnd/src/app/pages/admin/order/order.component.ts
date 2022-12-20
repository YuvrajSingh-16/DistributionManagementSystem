import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  order=[
    {
      orderId:'',
      date:'',
      orderStatus:'',
      totalAmount:'',
      quantity:'',
      userId:'',
      orderProductCategories: [
        { productCategories: 
          {
            categoryName:'',
            price:'',
          }
        }
      ]
    }
  ]
  constructor(private orderDetail:OrderService) { }

  ngOnInit(): void {
    this.orderDetail.order().subscribe(
      (data:any)=>{
        this.order=data;
        console.log(this.order);
      },
      (error)=>{
        Swal.fire("Error..!","Something Went Wrong","error");
      }
    )
  }

  displayedColumns: string[] = ['orderId', 'categoryName', 'price', 'quantity', 'totalAmount', 'orderStatus', 'date' , 'details'];
  dataSource = this.order;

}
