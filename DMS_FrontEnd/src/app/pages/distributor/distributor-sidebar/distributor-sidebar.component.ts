import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { GetRetailerComponent } from '../get-retailer/get-retailer.component';

@Component({
  selector: 'app-distributor-sidebar',
  templateUrl: './distributor-sidebar.component.html',
  styleUrls: ['./distributor-sidebar.component.css']
})
export class DistributorSidebarComponent implements OnInit {

  constructor(public login:LoginService, ) { }
  
  user=null;
  pincode=null;

  ngOnInit(): void {
    this.user= this.login.getUser();
    this.pincode=this.login.getUser().pincode;
    
  }
}
