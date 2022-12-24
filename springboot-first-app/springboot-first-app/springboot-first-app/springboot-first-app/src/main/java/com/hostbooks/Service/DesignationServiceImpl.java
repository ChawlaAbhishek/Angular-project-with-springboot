package com.hostbooks.Service;

import com.hostbooks.Dto.AddressDto;
import com.hostbooks.Dto.DesignationDto;
import com.hostbooks.entities.Designation;
import com.hostbooks.entities.Employee;
import com.hostbooks.exceptions.ResourceNotFoundException;
import com.hostbooks.repository.DesignationDao;
import com.hostbooks.repository.EmployeeDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DesignationServiceImpl implements DesignationService{
    @Autowired
    private EmployeeDao empDao;
    @Autowired
    private DesignationDao degDao;
    @Autowired
    private ModelMapper mp;
    @Override
    public DesignationDto createDesignation(DesignationDto designationDto, Integer empId) {

        Employee employee = empDao.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee does not exist with employee id"+" "+empId));

        Designation designation = mp.map(designationDto,Designation.class);

        if(employee.getDesignation()==null){

           // designation.setEmployee(employee);

            Designation savedDesignation = degDao.save(designation);

            return mp.map(savedDesignation,DesignationDto.class);


        }else{
            throw new ResourceNotFoundException("Designation is already given to employee");
        }



    }

    @Override
    public DesignationDto updateDesignation(DesignationDto designationDto, Integer empId, Integer degId) throws ResourceAccessException {

        Employee employee = empDao.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee does not exist with employee id"+" "+empId));

        Designation designation = degDao.findById(degId).orElseThrow(()->new ResourceNotFoundException("Designatin does not exist with designation id"+" "+degId));

        designation.setGrade(designationDto.getGrade());
       // designation.setEmployee(employee);

        Designation updatedDesignation = degDao.save(designation);

        return mp.map(updatedDesignation,DesignationDto.class);
    }

    @Override
    public List<DesignationDto> getAllDesignations() {
        List<Designation> designations = degDao.findAll();
        List<DesignationDto> designationDtos =designations.stream().map((designation) -> mp.map(designation,DesignationDto.class)).collect(Collectors.toList());
        return designationDtos;

    }

    @Override
    public DesignationDto getDesignationById(Integer degId) throws ResourceAccessException {

        Designation designation = degDao.findById(degId).orElseThrow(()->new ResourceNotFoundException("Designatin does not exist with designation id"+" "+degId));

        return mp.map(designation,DesignationDto.class);


    }

    @Override
    public void deleteDesignationById(Integer degId) throws ResourceNotFoundException {

        Designation designation = degDao.findById(degId).orElseThrow(()->new ResourceNotFoundException("Designatin does not exist with designation id"+" "+degId));
         degDao.delete(designation);




    }
}
