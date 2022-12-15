package com.hostbooks.Service;

import com.hostbooks.Dto.EmployeeDto;
import com.hostbooks.entities.Employee;
import com.hostbooks.exceptions.ResourceNotFoundException;
import com.hostbooks.repository.EmployeeDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private ModelMapper mp;
    @Autowired
    private EmployeeDao empDao;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = mp.map(employeeDto,Employee.class);
        Employee savedEmployee = empDao.save(employee);
        return mp.map(savedEmployee,EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(Integer empId, EmployeeDto employeeDto) throws ResourceNotFoundException {
        Employee employee = empDao.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee does not exist with employee id"+" "+empId));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setMobileNumber(employeeDto.getMobileNumber());
     //   employee.setAddresses(employeeDto.getAddresses());

        Employee updatedEmployee = empDao.save(employee);

        return mp.map(updatedEmployee,EmployeeDto.class);

    }

    @Override
    public EmployeeDto getEmployeeById(Integer empId) throws ResourceNotFoundException {
        Employee employee = empDao.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee does not exist with employee id"+" "+empId));

        return mp.map(employee,EmployeeDto.class);


    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employees =empDao.findAll();

       List<EmployeeDto> employeeDtos =  employees.stream().map((employee) -> mp.map(employee,EmployeeDto.class)).collect(Collectors.toList());
       return employeeDtos;
    }

    @Override
    public void deleteEmployee(Integer empId) throws ResourceNotFoundException {

        Employee employee = empDao.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee does not exist with employee id"+" "+empId));

        empDao.delete(employee);


    }
}
