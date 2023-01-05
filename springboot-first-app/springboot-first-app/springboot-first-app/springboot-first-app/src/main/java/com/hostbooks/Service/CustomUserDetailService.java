package com.hostbooks.Service;

import com.hostbooks.entities.CustomUserDetail;
import com.hostbooks.entities.User;
import com.hostbooks.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userDao.findByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("No user exist");
        }
        return new CustomUserDetail(user);
    }
}
