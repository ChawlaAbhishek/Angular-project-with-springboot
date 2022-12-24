import { Component,OnInit } from '@angular/core';

import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from '../address';
import { Designation } from '../designation';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  employee:Employee=new Employee();
  addresses:Address[]=[];
  designation:Designation=new Designation();


  id:any;



  ngOnInit():void{


    this.id=this.route.snapshot.params['id'];
    this.employeeService.getEmployeeById(this.id).subscribe(data=>{
      this.employee=data;
      this.addresses=data.addresses;
      this.designation=data.designation;
      console.log(this.addresses);

      //console.log(data);
    })

  }




  constructor(private employeeService:EmployeeService,private router:Router,private route:ActivatedRoute){

  }

  onSubmit(){
    console.log(this.employee);
    this.employeeService.updateEmployee(this.id,this.employee).subscribe(data=>{
      console.log(data);
      this.goToEmployeeList();
    })

  };

  
  goToEmployeeList(){

    this.router.navigate(['/employees']);
}




}
