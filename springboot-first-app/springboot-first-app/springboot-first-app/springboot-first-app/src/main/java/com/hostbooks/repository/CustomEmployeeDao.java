package com.hostbooks.repository;

import com.hostbooks.Dto.EmployeeResponse;
import com.hostbooks.entities.Address;
import com.hostbooks.entities.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomEmployeeDao{

    List<Employee> findEmployeeByFirstName(String firstName);

    List<Employee> findEmployeeByFirstNameAndDesignation(String firstName,String designation);

    List<Employee> findEmployeeInDescendingOrder();

    List<Employee> filterEmployeeBaseOnIdAndName(Integer empId,String name);

    boolean findEmployeeByMobile(String mobileNumber);

    List<String> fingEmployeeByNameThroughHql();

    List<Employee> findEmployeeByNameQuery();

    EmployeeResponse paginationThroughCriteria(String name,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

    }
