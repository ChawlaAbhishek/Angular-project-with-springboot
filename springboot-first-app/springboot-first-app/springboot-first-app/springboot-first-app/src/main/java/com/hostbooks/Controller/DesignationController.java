package com.hostbooks.Controller;

import com.hostbooks.Dto.DesignationDto;
import com.hostbooks.Service.DesignationService;
import com.hostbooks.Validator.DesignationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class DesignationController {

    @Autowired
    private DesignationService degService;
    @PostMapping("/designations/{empId}")
    ResponseEntity<?> createDesignationHandler(@RequestBody DesignationDto designationDto, @PathVariable Integer empId, BindingResult valid){

        new DesignationValidator().validate(designationDto,valid);

        DesignationDto savedDesignation = degService.createDesignation(designationDto,empId);

        return new ResponseEntity<>(savedDesignation, HttpStatus.ACCEPTED);

    }
    @PutMapping("/designations/{empId}/{degId}")
    ResponseEntity<?> updateDesignationHandler(@RequestBody DesignationDto designationDto,@PathVariable Integer empId,@PathVariable Integer degId,BindingResult valid){

        new DesignationValidator().validate(designationDto,valid);


        DesignationDto updatedDesignation = degService.updateDesignation(designationDto,empId,degId);

        return  new ResponseEntity<>(updatedDesignation,HttpStatus.ACCEPTED);
    }
    @GetMapping("/designations")
    ResponseEntity<List<DesignationDto>> getAllDesignations(){
        List<DesignationDto> designationDtoList =degService.getAllDesignations();

        return new ResponseEntity<>(designationDtoList,HttpStatus.ACCEPTED);
    }

    @GetMapping("/designations/{degId}")
    ResponseEntity<DesignationDto> getAllDesignations(@PathVariable Integer degId){
        DesignationDto designation =degService.getDesignationById(degId);

        return new ResponseEntity<>(designation,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/designations/{degId}")
    String deleteDesignationHandler(@PathVariable Integer degId){
        degService.deleteDesignationById(degId);

        return "designation deleted successfully";
    }
}
