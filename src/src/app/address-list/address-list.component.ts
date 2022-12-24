import { Component,OnInit } from '@angular/core';
import { Address } from '../address';
import { AddressService } from '../address.service';
import { Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-address-list',
  templateUrl: './address-list.component.html',
  styleUrls: ['./address-list.component.css']
})
export class AddressListComponent implements OnInit {

  addresses:any=Address;
  employee:any=Employee;

  ngOnInit(){
    this.getAddressList();

  }
  constructor(private addressService:AddressService,private router:Router,private employeeService:EmployeeService){}

  getAddressList(){
    this.addressService.getAddressList().subscribe(data=>{
      this.addresses=data;
      
    })
    this.employeeService.getEmployeesList().subscribe(data=>{
      this.employee=data;
     // console.log(this.employee);
    })
    this.router.navigate(["addresses"]);


  }
  updateAddress(addId:any,empId:any){

   // console.log(id);
    this.router.navigate(["update-address",addId,empId]);

  }

  deleteAddress(addId:any){

    this.addressService.deleteAddress(addId).subscribe(data=>{
       console.log(data);

    })
    window.location.reload();
}

}
