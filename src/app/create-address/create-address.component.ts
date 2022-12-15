import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from '../address';
import { AddressService } from '../address.service';
import { Employee } from '../employee';

@Component({
  selector: 'app-create-address',
  templateUrl: './create-address.component.html',
  styleUrls: ['./create-address.component.css']
})
export class CreateAddressComponent {

  address:Address = new Address();
  employee:Employee=new Employee();
  
  constructor(private addressService:AddressService,private router:Router){}

  onSubmit(){
   // console.log(this.address);
    console.log(this.employee.id);
   
    this.addressService.createAddress(this.address,this.employee.id).subscribe(data=>{
      console.log("data"+data);
      this.goToAddressList();

    })
    }

    goToAddressList(){
      this.router.navigate(['/addresses']);


    }

}
