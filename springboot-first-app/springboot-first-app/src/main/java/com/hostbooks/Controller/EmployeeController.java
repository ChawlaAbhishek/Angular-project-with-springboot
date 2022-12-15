package com.hostbooks.Controller;

import com.hostbooks.Dto.EmployeeDto;
import com.hostbooks.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:4200/")
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService empService;
    @PostMapping("/Employees")
    ResponseEntity<EmployeeDto> createEmployeeHandler(@RequestBody EmployeeDto employeeDto){

        EmployeeDto createdEmployee = empService.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
    @PutMapping("/Employees/{empId}")
    ResponseEntity<EmployeeDto> updateEmployeeHandler(@RequestBody EmployeeDto employeeDto,@PathVariable Integer empId){
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
}
