import { Component, OnInit } from '@angular/core';
import { GetRetailerService } from 'src/app/services/get-retailer.service';
import Swal from 'sweetalert2';
//import { ActivatedRoute } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-get-retailer',
  templateUrl: './get-retailer.component.html',
  styleUrls: ['./get-retailer.component.css']
})
export class GetRetailerComponent implements OnInit {
  //retailers=[]
  getRetailer=null;
  Id=undefined;
  constructor(private _retailer:GetRetailerService, public login:LoginService) { }

  ngOnInit(): void {

    this.Id=this.login.getUser().userId;
    console.log(this.Id);
    
    
    this._retailer.getRetailer(this.Id).subscribe((data:any)=>
    {
      this.getRetailer=data;
      console.log(this.getRetailer);
      
      
    },
    (error: any)=>{
      console.log(error);
      Swal.fire('Error!!','Error loading data from server','error');
            
    })

    
     
  
  }

  

}
  
  

