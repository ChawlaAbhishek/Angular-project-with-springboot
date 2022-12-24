package com.hostbooks.Controller;

import com.hostbooks.Dto.EmployeeDto;
import com.hostbooks.Service.EmployeeService;
import com.hostbooks.Validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin("http://localhost:4200/")
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService empService;

    @Autowired
    EmployeeValidator employeeValidator;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){

        webDataBinder.setValidator(employeeValidator);

    }
    @PostMapping("/Employees")
    ResponseEntity<?>createEmployeeHandler(@Valid @RequestBody EmployeeDto employeeDto, Errors errors){

       // new EmployeeValidator().validate(employeeDto,valid);
        if(errors.hasErrors()){
            return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }

        EmployeeDto createdEmployee = empService.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
    @PutMapping("/Employees/{empId}")
    ResponseEntity<?> updateEmployeeHandler(@Valid @RequestBody EmployeeDto employeeDto,@PathVariable Integer empId, Errors errors){

       // new EmployeeValidator().validate(employeeDto,valid);
        if(errors.hasErrors()){
            return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }



        EmployeeDto updatedEmployee = empService.updateEmployee(empId,employeeDto);

        return new ResponseEntity<>(updatedEmployee,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/Employees/{empId}")
    String deleteEmployeeHandler(@PathVariable Integer empId){
        empService.deleteEmployee(empId);

        return "employee deleted successfully";
    }
    @GetMapping("/Employees/{empId}")
    ResponseEntity<EmployeeDto> getEmployeeByIdHanlder(@PathVariable Integer empId){

        EmployeeDto employeeDto =empService.getEmployeeById(empId);

        return new ResponseEntity<>(employeeDto,HttpStatus.ACCEPTED);

    }
    @GetMapping("/Employees")
    ResponseEntity<List<EmployeeDto>> getAllEmployeesHandler(){
        List<EmployeeDto> employeeDtos = empService.getAllEmployees();

        return new ResponseEntity<>(employeeDtos,HttpStatus.ACCEPTED);
    }

    @GetMapping("/EmployeesCustom/{firstName}")
    ResponseEntity<List<EmployeeDto>> getEmployeeByFirstName(@PathVariable String firstName){
        List<EmployeeDto> employeeDtos = empService.findEmployeeByFirstName(firstName);

        return new ResponseEntity<>(employeeDtos,HttpStatus.ACCEPTED);
    }

    @GetMapping("/EmployeesCustom/{firstName}/{designation}")
    ResponseEntity<List<EmployeeDto>> getEmployeeByFirstNameAndDesigantion(@PathVariable String firstName,@PathVariable String designation){
        List<EmployeeDto> employeeDtos = empService.findEmployeeByFirstNameAndDesignation(firstName,designation);

        return new ResponseEntity<>(employeeDtos,HttpStatus.ACCEPTED);
    }

    @GetMapping("/EmployeesCustom")
    ResponseEntity<List<EmployeeDto>> getEmployeesByOrderHandler(){
        List<EmployeeDto> employeeDtos = empService.getEmployeesByOrder();

        return new ResponseEntity<>(employeeDtos,HttpStatus.ACCEPTED);
    }

    @GetMapping("/EmployeesCustoms/{empId}/{name}")
    ResponseEntity<List<EmployeeDto>> filterEmployeeHandler(@PathVariable Integer empId,@PathVariable String name){
        List<EmployeeDto> employeeDtos = empService.filterEmployee(empId,name);

        return new ResponseEntity<>(employeeDtos,HttpStatus.ACCEPTED);
    }
}
