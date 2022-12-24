import { Component,OnInit } from '@angular/core';
import { Designation } from '../designation';
import { ActivatedRoute } from '@angular/router';
import { DesignationService } from '../designation.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-designation',
  templateUrl: './create-designation.component.html',
  styleUrls: ['./create-designation.component.css']
})
export class CreateDesignationComponent implements OnInit{

  constructor(private route:ActivatedRoute,private designationService:DesignationService,private router:Router){}

  ngOnInit(): void {
    this.empId =this.route.snapshot.params['empId'];
  }
  
  designation:Designation=new Designation();
  empId:any;

  onSubmit(){

    this.designationService.createDesignation(this.designation,this.empId).subscribe(data=>{
     // console.log(data);
      this.router.navigate(['employees']);

    })

    

  }

  

  

}
