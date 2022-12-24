import { Component,OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Designation } from '../designation';
import { DesignationService } from '../designation.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-update-designation',
  templateUrl: './update-designation.component.html',
  styleUrls: ['./update-designation.component.css']
})
export class UpdateDesignationComponent implements OnInit{

  empId:any;
  degId:any;
  designation:any=Designation;

  ngOnInit(): void {

    this.empId=this.route.snapshot.params['empId'];
    this.degId=this.route.snapshot.params['degId'];
    this.designationService.getDesignationById(this.degId).subscribe(data=>{
      this.designation=data;
      console.log(data);
    })

    
  }

  constructor(private route:ActivatedRoute,private designationService:DesignationService,private router:Router){

  }



  onSubmit(){

    this.designationService.updateDesignation(this.designation,this.empId,this.degId).subscribe(data=>{
      console.log(data);
      this.goToDesignationList();

    })

  }

  goToDesignationList(){
    this.router.navigate(['designations']);
  }

}
