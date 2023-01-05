import { Component,OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { AuthService } from './auth.service';
import { User } from './user';
import { AuthInterceptorInterceptor } from '../auth-interceptor.interceptor';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  ngOnInit(): void {

    
  }

  constructor(private route:ActivatedRoute,private router:Router,private authService:AuthService){

  }

   //user: User =new User();

   username: any;
   password : any;
  // errorMessage = 'Invalid Credentials';
  // successMessage?: string;
  // invalidLogin = false;
   loginSuccess=false;
   
 
  

  handleLogin(){
    //console.log(this.user.username)

    localStorage.setItem("username",this.username)
    localStorage.setItem("password",this.password)

    this.authService.authenticationService().subscribe(data=>{
       this.loginSuccess=true;
      console.log(this.loginSuccess)

      console.log(data);
       this.router.navigate(["employees/"])
      

    },error=>console.log(error))
    


    //this.authInterceptor.sendToInterceptor(this.username,this.password);
    }
    

    isLoggedIn(){
      console.log(this.loginSuccess);
      
      if(this.loginSuccess==false){
        return true;
      }else{
        return false;
      }
    }
}
