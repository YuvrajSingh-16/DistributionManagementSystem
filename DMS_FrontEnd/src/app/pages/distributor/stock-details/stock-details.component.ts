import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { StockDetailsService } from 'src/app/services/stock-details.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-stock-details',
  templateUrl: './stock-details.component.html',
  styleUrls: ['./stock-details.component.css']
})
export class StockDetailsComponent implements OnInit {

  stockDetails: any=[
    {
      availableQuantity:'',
      productCategories:{
          categoryId:'',
          categoryName:'',
          price:'',
          products: {
              productId:'',
              productName:'',
          }
      },
      id:'',
  },
  ];
  userID: any;

  constructor(private _stock:StockDetailsService, public login:LoginService) { }

  ngOnInit(): void {
    this.userID=this.login.getUser().userId;
    console.log(this.userID);

    this._stock.stockDetails(this.userID).subscribe((data: any)=>
    {
      this.stockDetails=data;
      console.log(this.stockDetails);  
    },
    (error)=>{
      console.log(error);
      Swal.fire('Error!!',"Error in loading data from server",'error');
      
    }
    )
  }

  displayedColumns: string[] = ['productId','categoryId','productName','categoryName','price','availableQuantity','addMoreStock'];
  dataSource = this.stockDetails;
}
