package com.hostbooks.repository;

import com.hostbooks.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeDao  extends JpaRepository<Employee,Integer> {

   // Page<Employee> findByFirstName(String name, Pageable pageable);


//    @Query("select e from Employee where e.firstName like:filter"+"or e.lastName like : filter or e.mobileNumber like:filter")
//    Page<Employee> filter(@Param("filter")String filter);

    @Query("SELECT e FROM Employee e WHERE (:name is null or e.firstName = :name) OR (:name is null or e.lastName = :name)OR (:name is null or e.mobileNumber = :name)OR (:name is null or e.empId = :name)OR (:name is null or e.designation.grade = :name)")
    Page<Employee> search(@Param("name") String name,  Pageable pageable);



}
