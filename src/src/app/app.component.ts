import { Component } from '@angular/core'
import { Router } from '@angular/router';
import { LoginComponent } from './login/login.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Employee-Details';

  constructor(private router:Router,private login:LoginComponent){}

  

  logout(){

    // console.log(this.login.loginSuccess)
    // this.login.loginSuccess=true;
    localStorage.clear();

    this.router.navigate(['login']);

  };

}
 