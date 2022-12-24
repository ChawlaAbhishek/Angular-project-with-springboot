package com.hostbooks.Validator;

import com.hostbooks.Dto.AddressDto;
import com.hostbooks.exceptions.ResourceNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AddressValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AddressDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        AddressDto addressDto =(AddressDto) target;

        if(addressDto.getCity().length()<=0||addressDto.getCity()==null){
            throw new ResourceNotFoundException("Please enter a address");
        }
        if(addressDto.getState().length()<=0||addressDto.getState()==null){
            throw new ResourceNotFoundException(("Please enter a state"));
        }
        if(addressDto.getCountry().length()<=0||addressDto.getCountry()==null){
            throw new ResourceNotFoundException("Please enter a country");
        }

    }
}
