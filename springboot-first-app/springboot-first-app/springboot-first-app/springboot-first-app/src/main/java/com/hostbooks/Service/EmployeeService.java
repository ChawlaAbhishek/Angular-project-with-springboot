package com.hostbooks.Service;

import com.hostbooks.Dto.EmployeeDto;
import com.hostbooks.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(Integer empId,EmployeeDto employeeDto) throws ResourceNotFoundException;

    EmployeeDto getEmployeeById(Integer empId) throws ResourceNotFoundException;
    List<EmployeeDto> getAllEmployees();

    void deleteEmployee(Integer empId) throws ResourceNotFoundException;

    List<EmployeeDto> findEmployeeByFirstName(String firstName);

    List<EmployeeDto> findEmployeeByFirstNameAndDesignation(String firstName,String designation);

    List<EmployeeDto> getEmployeesByOrder();

    List<EmployeeDto> filterEmployee(Integer empId,String name);



}
