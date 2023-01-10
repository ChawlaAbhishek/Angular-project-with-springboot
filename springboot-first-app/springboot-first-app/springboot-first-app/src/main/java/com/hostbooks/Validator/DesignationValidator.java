package com.hostbooks.Validator;

import com.hostbooks.Dto.DesignationDto;
import com.hostbooks.exceptions.ResourceNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DesignationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return DesignationDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        DesignationDto designationDto = (DesignationDto) target;

        if(designationDto.getGrade().length()<=0||designationDto.getGrade()==null)
            throw  new ResourceNotFoundException("Please enter a grade");


    }
}
