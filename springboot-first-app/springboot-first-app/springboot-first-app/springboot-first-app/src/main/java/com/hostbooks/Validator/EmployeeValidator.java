package com.hostbooks.Validator;

import com.hostbooks.Controller.EmployeeController;
import com.hostbooks.Dto.AddressDto;
import com.hostbooks.Dto.EmployeeDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

@ControllerAdvice(assignableTypes = EmployeeController.class)
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        EmployeeDto employee = (EmployeeDto) target;

        if (employee.getFirstName().length() == 0 || employee.getFirstName() == null) {
            // throw new EmployeeException("Enter the Employee firstname");

            errors.rejectValue("firstName", "404", "please enter the first name");
        }
        if (employee.getLastName().length() == 0 || employee.getLastName() == null) {
            //throw new EmployeeException("Enter the Employee lastname");
            errors.rejectValue("lastName", "404", "please entr the last name");
        }
        if (employee.getMobileNumber().length() < 10 || employee.getMobileNumber() == null) {
            // throw new EmployeeException("Enter valid mobile number");
            errors.rejectValue("mobileNumber", "404", "please enter the valid mobile number");
        }

        if (employee.getDesignation().getGrade().length() <= 0 || employee.getDesignation().getGrade() == null) {
            errors.rejectValue("designation", "404", "please enter the employee grade");
        }

        List<AddressDto> addresses =employee.getAddresses();

        for(AddressDto address:addresses){

            if(address.getCity().length()==0||address.getCity()==null){

                errors.rejectValue("addresses","404","please enter the city name");

            }
            if(address.getState().length()==0||address.getState()==null){

                errors.rejectValue("addresses","404","please enter the state name");


            }
            if(address.getCountry().length()==0||address.getCountry()==null){

                errors.rejectValue("addresses","404","please enter the country name");

            }







        }





    }
}
