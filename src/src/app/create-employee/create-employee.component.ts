import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { AddressService } from '../address.service';
import { Address } from '../address';
import { Designation } from '../designation';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  // city:any;
  // state:any;
  // country:any;

  employee:Employee=new Employee();
  Address=new Address();
  designation:Designation=new Designation();

  constructor(private employeeService:EmployeeService,private router:Router,private addressService:AddressService){

  }
  dataArray:any;
  

  
  ngOnInit():void{
    this.dataArray=[];
    this.Address = new Address();

    this.dataArray.push(this.Address);

  }

  saveEmployee(){
    this.employeeService.createEmployee(this.employee).subscribe(data=>{
      console.log(data);
      this.goToEmployeeList();
    },error=>
    alert(error.error.message))
  };

  goToEmployeeList(){
    this.router.navigate(['/employees']);

  }

  onSubmit(){
    //console.log(this.designation);
    this.employee.designation=this.designation;
    //console.log(this.employee);

    //console.log(this.dataArray);

     //let arr:any=[];
  

    //arr.push({city:data.city,state:data.state,country:data.country});


  

    
    //console.log(object);
    this.employee.addresses=this.dataArray;
    //console.log(this.employee);
    this.saveEmployee();

  };

  addForm(){
    this.Address = new Address();
    this.dataArray.push(this.Address);
  }


}
