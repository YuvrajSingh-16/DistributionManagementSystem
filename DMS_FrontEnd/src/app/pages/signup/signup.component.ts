import { Component, OnInit } from '@angular/core';
import { enableProdMode } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService:UserService,private snack:MatSnackBar) { }


  public user={
    emailId:'',
    firstName:'',
    contactDetails:'',
    lastName:'',
    password:'',
    address:'',
    pincode:'',
  }
  ngOnInit(): void {
  }

  reload(){
    window.location.reload();
  }

  formSubmit(){
  
    
    if(this.user.password=='' || this.user.password==null)
    {
      this.snack.open("Password is Required..!!", 'Ok' , { duration:3000});
      return;
    }
    if(this.user.firstName=='' || this.user.firstName==null)
    {
      this.snack.open("Firstname is Required..!!", 'Ok' , { duration:3000});
      return;
    }
    if(this.user.lastName=='' || this.user.lastName==null)
    {
      this.snack.open("Lastname is Required..!!", 'Ok' , { duration:3000});
      return;
    }
    if(this.user.emailId=='' || this.user.emailId==null)
    {
      this.snack.open("Email is Required..!!", 'Ok' , { duration:3000});
      return;
    }
    if(this.user.contactDetails=='' || this.user.contactDetails==null)
    {
      this.snack.open("Phone number is Required..!!", 'Ok' , { duration:3000});
      return;
    }
    if(this.user.address=='' || this.user.address==null)
    {
      this.snack.open("Address is Required..!!", 'Ok' , { duration:3000});
    }
    if(this.user.pincode=='' || this.user.pincode==null)
    {
      this.snack.open("Pincode is Required..!!", 'Ok' , { duration:3000});
    }
    //adduser:userservice
    this.userService.adduser(this.user).subscribe(
      (data)=>{
        //succes
        console.log(data);
        //alert('succes');
       Swal.fire('Success', 'User is successfully Registered','success').then((value) => {
        window.location.href='/login'
      });
      },
      (error)=>{
          //error
          console.log(error);
          alert('something went wrong');
      }
    )
  }

  //this.user


}