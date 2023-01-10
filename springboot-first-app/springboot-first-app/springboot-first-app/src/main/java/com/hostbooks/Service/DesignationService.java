package com.hostbooks.Service;

import com.hostbooks.Dto.DesignationDto;
import com.hostbooks.exceptions.ResourceNotFoundException;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

public interface DesignationService {

    DesignationDto createDesignation(DesignationDto designationDto,Integer empId);

    DesignationDto updateDesignation(DesignationDto designationDto,Integer empId,Integer degId) throws ResourceAccessException;
    List<DesignationDto> getAllDesignations();

    DesignationDto getDesignationById(Integer degId) throws  ResourceAccessException;

    void deleteDesignationById(Integer degId) throws ResourceNotFoundException;



}
