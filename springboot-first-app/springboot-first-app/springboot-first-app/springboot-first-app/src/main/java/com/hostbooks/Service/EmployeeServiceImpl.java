package com.hostbooks.Service;

import com.hostbooks.Dto.AddressDto;
import com.hostbooks.Dto.DesignationDto;
import com.hostbooks.Dto.EmployeeDto;
import com.hostbooks.entities.Address;
import com.hostbooks.entities.Designation;
import com.hostbooks.entities.Employee;
import com.hostbooks.exceptions.ResourceNotFoundException;
import com.hostbooks.repository.CustomEmployeeDao;
import com.hostbooks.repository.EmployeeDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private ModelMapper mp;
    @Autowired
    private EmployeeDao empDao;
    @Autowired
    private CustomEmployeeDao customEmployeeDao;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = mp.map(employeeDto, Employee.class);

        boolean flag =customEmployeeDao.findEmployeeByMobile(employee.getMobileNumber());
        if(flag==false){

            Employee savedEmployee = empDao.save(employee);

            return mp.map(savedEmployee, EmployeeDto.class);

        }else{
            throw new ResourceNotFoundException("Mobile number already exists");
        }


    }

    @Override
    public EmployeeDto updateEmployee(Integer empId, EmployeeDto employeeDto) throws ResourceNotFoundException {

        Employee employee = empDao.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with employee id" + " " + empId));


        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setMobileNumber(employeeDto.getMobileNumber());
        DesignationDto designationDto = employeeDto.getDesignation();
        Designation designation = mp.map(designationDto, Designation.class);
        employee.setDesignation(designation);
        List<AddressDto> addressDtos = employeeDto.getAddresses();
        List<Address> addresses = addressDtos.stream().map((addressDto) -> mp.map(addressDto, Address.class)).collect(Collectors.toList());

        employee.setAddresses(addresses);

        //  employee.setAddresses(employeeDto.getAddresses());

        Employee updatedEmployee = empDao.save(employee);

        return mp.map(updatedEmployee, EmployeeDto.class);

    }

    @Override
    public EmployeeDto getEmployeeById(Integer empId) throws ResourceNotFoundException {
        Employee employee = empDao.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with employee id" + " " + empId));

        return mp.map(employee, EmployeeDto.class);


    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employees = empDao.findAll();

        List<EmployeeDto> employeeDtos = employees.stream().map((employee) -> mp.map(employee, EmployeeDto.class)).collect(Collectors.toList());
        return employeeDtos;
    }

    @Override
    public void deleteEmployee(Integer empId) throws ResourceNotFoundException {

        Employee employee = empDao.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with employee id" + " " + empId));

        empDao.delete(employee);


    }

    @Override
    public List<EmployeeDto> findEmployeeByFirstName(String firstName) {

        List<Employee> employees = customEmployeeDao.findEmployeeByFirstName(firstName);

        List<EmployeeDto> employeeDtos = employees.stream().map((employee) -> mp.map(employee, EmployeeDto.class)).collect(Collectors.toList());

        return employeeDtos;


    }

    @Override
    public List<EmployeeDto> findEmployeeByFirstNameAndDesignation(String firstName, String designation) {

        List<Employee> employees = customEmployeeDao.findEmployeeByFirstNameAndDesignation(firstName, designation);

        List<EmployeeDto> employeeDtos = employees.stream().map((employee) -> mp.map(employee, EmployeeDto.class)).collect(Collectors.toList());

        return employeeDtos;

    }

    @Override
    public List<EmployeeDto> getEmployeesByOrder() {

        List<Employee> employees = customEmployeeDao.findEmployeeInDescendingOrder();

        List<EmployeeDto> employeeDtos = employees.stream().map((employee) -> mp.map(employee, EmployeeDto.class)).collect(Collectors.toList());

        return employeeDtos;


    }

    @Override
    public List<EmployeeDto> filterEmployee(Integer empId, String name) {

        List<Employee> employees = customEmployeeDao.filterEmployeeBaseOnIdAndName(empId, name);

        List<EmployeeDto> employeeDtos = employees.stream().map((employee) -> mp.map(employee, EmployeeDto.class)).collect(Collectors.toList());

        return employeeDtos;


    }
}
