import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  
 
  constructor(public login:LoginService) {}
  
  ngOnInit(): void {
  }

  public routing(){
    if(this.login.getUserRole()=="ROLE_ADMIN")
            {
              window.location.href='/admin-dashboard/update-profile';
            }
            else if(this.login.getUserRole()=="ROLE_DISTRIBUTOR")
            {
              window.location.href='/distributor-dashboard/update-profile';
            }
            else if(this.login.getUserRole()=="ROLE_RETAILER")
            {
              window.location.href='/retailer-dashboard/update-profile';
            }
  }

}
