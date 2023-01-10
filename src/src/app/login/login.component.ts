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

  constructor(private route:ActivatedRoute,private router:Router,private authService:AuthService){}

   user: User =new User();

  //  username: any;
  //  password : any;
  // errorMessage = 'Invalid Credentials';
  // successMessage?: string;
  // invalidLogin = false;
   //loginSuccess=false;
   
 
  

   handleLogin(){
    console.log(this.user.username)

    // localStorage.setItem("username",this.user.username)
    // localStorage.setItem("password",this.user.password)

    // this.authService.authenticationService().subscribe(data=>{
    //    this.loginSuccess=true;
    //   console.log(this.loginSuccess)

    //   console.log(data);
    //    this.router.navigate(["employees/"])
      

    // },error=>console.log(error))
    this.authService.authenticateService(this.user).subscribe((data:any)=>{
      console.log(data.token);
    //  this.loginSuccess=true;

      localStorage.setItem("token",data.token);
              this.router.navigate(["employees/"])

    })
    


    //this.authInterceptor.sendToInterceptor(this.username,this.password);
    }

    

    isLoggedIn(){
      // this.loginSuccess=false;
      
      // console.log(this.loginSuccess);
      
      // if(this.loginSuccess){
      //   return true;
      // }else{
      //   return false;
      // }

      if(localStorage.getItem("token")){
      return true;
    }else{
      return false;
    }
  }
  
}
