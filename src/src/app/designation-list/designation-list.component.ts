import { Component,OnInit } from '@angular/core';
import { Designation } from '../designation';
import { DesignationService } from '../designation.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-designation-list',
  templateUrl: './designation-list.component.html',
  styleUrls: ['./designation-list.component.css']
})
export class DesignationListComponent implements OnInit {

  designations:any=Designation;
  constructor(private designationService:DesignationService,private router:Router){}

  ngOnInit(){
    this.designationService.getDesignationList().subscribe(data=>{
      this.designations=data;
     // console.log(data);
    })

  }

  // updateDesignation(degId:any,empId:any){

  //   this.router.navigate(['update-designation',degId,empId])

    

  // }

  deleteDesignation(degId:any){

    this.designationService.deleteDesignation(degId).subscribe(data=>{
      console.log(data);
    })
    window.location.reload();

  }



}
