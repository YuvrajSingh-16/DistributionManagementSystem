import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-welcome-dist',
  templateUrl: './welcome-dist.component.html',
  styleUrls: ['./welcome-dist.component.css']
})
export class WelcomeDistComponent implements OnInit {

  user=null;
  pincode=null;

  constructor(public login:LoginService) { }

  ngOnInit(): void {
    this.user= this.login.getUser();
    this.pincode=this.login.getUser().pincode;
  }

}
