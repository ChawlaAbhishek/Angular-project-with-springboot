package com.hostbooks.Controller;

import com.hostbooks.Service.CustomUserDetailService;
import com.hostbooks.entities.CustomUserDetail;
import com.hostbooks.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200/")
public class BasicAuthController {

    @GetMapping("/basicAuth")
    @PreAuthorize("hasRole('ADMIN')")
    public String basicAuth(){

        //UserDetails customUserDetail =customUserDetailService.loadUserByUsername(user.getUsername());

        return "authenticated";





    }
}
