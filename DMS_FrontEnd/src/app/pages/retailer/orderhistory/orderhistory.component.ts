import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { OrderService } from 'src/app/services/order.service'
import Swal from 'sweetalert2';

@Component({
  selector: 'app-orderhistory',
  templateUrl: './orderhistory.component.html',
  styleUrls: ['./orderhistory.component.css']
})
export class OrderhistoryComponent implements OnInit {

  orders=[
    {
      orderId: '',
      date: '',
      orderStatus: '',
      totalAmount: '',
      quantity: '',
      userId: '',
      categoryId: '',
      orderProductCategories: [
          {
              id:'',
              productCategories: {
                  categoryId: '',
                  categoryName: '',
                  price: '',
                  products: {
                      productId: '',
                      productName:''
                  }
              }
          }
      ]
  },
  ];

  constructor(public order: OrderService,public login: LoginService) { }

  ngOnInit(): void {
    let name=this.login.getUser().userId
    console.log(name)
    this.order.getOrderHistory(name).subscribe((data:any) => {
      //css
      this.orders = data;
      console.log("Loading Order History");
      console.log(this.orders);
    },
    (error)=> {
      console.log(error);
      Swal.fire('Error !!', 'Error in loading order history', 'error');
    }
    );
  }
  displayedColumns: string[] = ['orderId','productName','categoryName','price', 'quantity', 'totalAmount','orderStatus','date'];
  dataSource = this.orders;
}

