package com.hostbooks.Service;

import com.hostbooks.Dto.AddressDto;
import com.hostbooks.Dto.DesignationDto;
import com.hostbooks.Dto.EmployeeDto;
import com.hostbooks.Dto.EmployeeResponse;
import com.hostbooks.entities.Address;
import com.hostbooks.entities.Designation;
import com.hostbooks.entities.Employee;
import com.hostbooks.exceptions.ResourceNotFoundException;
import com.hostbooks.repository.CustomEmployeeDao;
import com.hostbooks.repository.EmployeeDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        }
        else{
            throw new ResourceNotFoundException("Mobile number already exists");
        }


    }

    @Override
    public EmployeeDto updateEmployee(Integer empId, EmployeeDto employeeDto) throws ResourceNotFoundException {

        Employee employee = empDao.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with employee id" + " " + empId));

       // boolean flag =customEmployeeDao.findEmployeeByMobile(employee.getMobileNumber());

        //if(flag==false){

            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setMobileNumber(employeeDto.getMobileNumber());
            DesignationDto designationDto = employeeDto.getDesignation();
            Designation designation = mp.map(designationDto, Designation.class);
            employee.setDesignation(designation);
            List<AddressDto> addressDtos = employeeDto.getAddresses();
            List<Address> addresses = addressDtos.stream().map((addressDto) -> mp.map(addressDto, Address.class)).collect(Collectors.toList());
//            if(addresses.size()==0){
//
//
//
//            }

            employee.setAddresses(addresses);

            //  employee.setAddresses(employeeDto.getAddresses());

            Employee updatedEmployee = empDao.save(employee);

            return mp.map(updatedEmployee, EmployeeDto.class);

//        }else{
//            throw new ResourceNotFoundException("Mobile number already exists");
//        }
    }

    @Override
    public EmployeeDto getEmployeeById(Integer empId) throws ResourceNotFoundException {
        Employee employee = empDao.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with employee id" + " " + empId));

        return mp.map(employee, EmployeeDto.class);

    }

    @Override
    public EmployeeResponse getAllEmployees(String name,Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else{
            sort=Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Employee> pageEmployee=null;

        if(name.equalsIgnoreCase("")){
             pageEmployee = empDao.findAll(pageable);

        }else {
           pageEmployee= empDao.search(name,pageable);
        }

        //Page<Employee> pageEmployee = empDao.findAll(pageable);

        List<Employee> employees = pageEmployee.getContent();

        List<EmployeeDto> employeeDtos = employees.stream().map((employee) -> mp.map(employee, EmployeeDto.class)).collect(Collectors.toList());

        EmployeeResponse employeeResponse = new EmployeeResponse();

        employeeResponse.setContent(employeeDtos);
        employeeResponse.setPageNumber(pageEmployee.getNumber());
        employeeResponse.setPageSize(pageEmployee.getSize());
        employeeResponse.setTotalElements(pageEmployee.getTotalElements());
        employeeResponse.setTotalPages(pageEmployee.getTotalPages());
        employeeResponse.setLastPage(pageEmployee.isLast());

        return employeeResponse;
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
