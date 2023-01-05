import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Designation } from '../designation';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { DesignationService } from '../designation.service';
import { EmployeeResponse } from '../employee-response';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit{



  employees:any=Employee;
  employeeResponse:any=EmployeeResponse;
  designation:any=Designation;
  
  sortBy:any="empId";
  sortDir:any="asc";
  pageNumber:any=0;
  pageSize:any=2;
  totalRecords:any;
  name="";
  
  

  employeeCustom:any=[];

  constructor(private employeeService:EmployeeService, private router:Router,private designationService:DesignationService){}
  

  ngOnInit(): void {
   // this.getEmployees();
    this.getEmployeeByPagination();
    
  }

  // private getEmployees(){
  //   this.employeeService.getEmployeesList().subscribe(data=>{
  //     this.employeeResponse=data; 
  //     this.employees=this.employeeResponse.content; 
    
      
  //     console.log(this.employeeResponse,"employee");
  //     //this.designation=data[0].designation;
  //     //console.log(this.designation);
  //     //console.log(this.employees);
  //    // console.log(data)
  //   });
  //   this.designationService.getDesignationList().subscribe(data=>{
  //     this.designation=data;
  //    // console.log(data);
  //     //console.log("abhishek"+this.designation[0].employee.empId);
  //   })
  // }

  updateEmployee(id:any){
    //console.log(id);
    this.router.navigate(['update-employee',id]);
  }

  deleteEmployee(id:number){

    this.employeeService.deleteEmployee(id).subscribe(data=>{

     // console.log(data);
      //this.getEmployees();
      this.getEmployeeByPagination();

      
    })
    window.location.reload();

  }

  viewEmployee(id:number){
    this.router.navigate(['employee-details',id]);
  }

  getEmployeeByPagination(){
    this.employeeService.getEmployeeByPagination(this.name,this.pageNumber,this.pageSize,this.sortBy,this.sortDir).subscribe(data=>{
      this.employeeResponse=data; 
      this.employees=this.employeeResponse.content; 
      this.totalRecords=data.totalElements;
    
      
      console.log(this.employeeResponse,"employee");
      //this.designation=data[0].designation;
      //console.log(this.designation);
      //console.log(this.employees);
     // console.log(data)
    })
    this.designationService.getDesignationList().subscribe(data=>{
      this.designation=data;
     // console.log(data);
      //console.log("abhishek"+this.designation[0].employee.empId);
    })
  }
  

  onTableDataChange(event:any){

    this.pageNumber=event-1;
    this.getEmployeeByPagination();

  }

  
  

  flag:any=true;
  buttonId:any="sort";
  sortById(){

    this.employees.sort((a:any,b:any)=>{
      if(a.empId>b.empId){
        return 1;
      }else{
        return -1;
      }

    })
   // this.sortBy="empId";
    if(this.flag==true){
      this.buttonId="desc";
      //this.sortDir="asc";
      this.flag=false;
     // this.getEmployeeByPagination();

    }else{
      this.buttonId="asc";
      //this.sortDir="desc";
      this.flag=true;
      this.employees.sort((a:any,b:any)=>{
        if(a.empId>b.empId){
          return -1;
        }else{
          return 1;
        }
      })
    //  this.getEmployeeByPagination();
      

      
    }

  }
  buttonFN:any="sort";
  sortByFirstName(){
    this.employees.sort((a:any,b:any)=>{
      if(a.firstName>b.firstName){
        return 1;

      }else{
        return -1;
      }
    })
    this.buttonId="sort";
    //this.sortBy="firstName";
    if(this.flag==true){
      this.buttonFN="desc";
      //this.sortDir="asc";
      this.flag=false;
     // this.getEmployeeByPagination();
    }else{
      this.buttonFN="asc";
      //this.sortDir="desc";
      this.flag=true;
      //this.getEmployeeByPagination();
      this.employees.sort((a:any,b:any)=>{
        if(a.firstName>b.firstName){
          return -1;
  
        }else{
          return 1;
        }
      })
    }

  }

  buttonLN:any="sort";
  sortByLastName(){
    this.employees.sort((a:any,b:any)=>{
      if(a.lastName>b.lastName){
        return 1;

      }else{
        return -1;
      }
    })
    this.buttonFN="sort";
   // this.sortBy="lastName";
    if(this.flag==true){
      this.buttonLN="desc";
     // this.sortDir="asc";
      this.flag=false;
      //this.getEmployeeByPagination();

    }else{
      this.buttonLN="asc";

      this.employees.sort((a:any,b:any)=>{
        if(a.lastName>b.lastName){
          return +1;
  
        }else{
          return 1;
        }
      })
     // this.sortDir="desc";
      this.flag=true;
      //this.getEmployeeByPagination();
    }
  }

  buttonMN:any="sort";
  sortByMobileNumber(){
    this.buttonLN="sort";
    this.employees.sort((a:any,b:any)=>{
      if(a.mobileNumber>b.mobileNumber){
        return 1;

      }else{
        return -1;
      }
    })
   // this.sortBy="mobileNumber";
    if(this.flag==true){
      this.buttonMN="desc";
     // this.sortDir="asc";
      this.flag=false;
     // this.getEmployeeByPagination();

    }else{
      this.buttonMN="asc";
     // this.sortDir="desc";
      this.flag=true;
      //this.getEmployeeByPagination();
      this.employees.sort((a:any,b:any)=>{
        if(a.mobileNumber>b.mobileNumber){
          return -1;
  
        }else{
          return 1;
        }
      })
    }
  }

   buttonD="sort";
  sortByDesignation(){

    this.buttonMN="sort";
    this.employees.sort((a:any,b:any)=>{
      if(a.designation.grade>b.designation.grade){
        return 1;

      }else{
        return -1;
      }
    })
   // this.sortBy="mobileNumber";
    if(this.flag==true){
      this.buttonD="desc";
     // this.sortDir="asc";
      this.flag=false;
     // this.getEmployeeByPagination();

    }else{
      this.buttonD="asc";
     // this.sortDir="desc";
      this.flag=true;
      //this.getEmployeeByPagination();
      this.employees.sort((a:any,b:any)=>{
        if(a.designation.grade>b.designation.grade){
          return -1;
  
        }else{
          return 1;
        }
      })
    }

  }
  getValue(data:any){
    this.name=data;
    this.pageNumber=0;
    this.getEmployeeByPagination();

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
