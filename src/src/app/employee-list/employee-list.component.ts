import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Designation } from '../designation';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { DesignationService } from '../designation.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit{



  employees:any=Employee;
  designation:any=Designation;

  constructor(private employeeService:EmployeeService, private router:Router,private designationService:DesignationService){}
  

  ngOnInit(): void {
    this.getEmployees();
    
  }

  private getEmployees(){
    this.employeeService.getEmployeesList().subscribe(data=>{
      this.employees=data;
      //this.designation=data[0].designation;
      //console.log(this.designation);
      //console.log(this.employees);
      //console.log(data)
    });
    this.designationService.getDesignationList().subscribe(data=>{
      this.designation=data;
      console.log(data);
      //console.log("abhishek"+this.designation[0].employee.empId);
    })
  }

  updateEmployee(id:any){
    //console.log(id);
    this.router.navigate(['update-employee',id]);
  }

  deleteEmployee(id:number){

    this.employeeService.deleteEmployee(id).subscribe(data=>{

     // console.log(data);
      this.getEmployees();

      
    })
    window.location.reload();

  }

  viewEmployee(id:number){
    this.router.navigate(['employee-details',id]);
  }

  // addAddress(id:number){
  //   this.router.navigate(['create-address',id]);

  // }
  // addDesignation(empId:any){

  //   let flag=true;

  //   for(let i =0;i<this.designation.length;i++){
  //     if(this.designation[i].employee.empId==empId){
  //       flag=false;
  //       alert("designation is already assigned to employee with id"+empId);
  //     }
  //   }
  //   if(flag){
  //     this.router.navigate(['create-designation',empId]);
  //   }
  // }
}
