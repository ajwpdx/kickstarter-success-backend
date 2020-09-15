package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.exceptions.ResourceNotFoundException;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "securityUserService")
public class SecurityUserServiceImpl implements UserDetailsService
{
    //this is where we determine whether username and password is a valid combination
    @Autowired
    private UserRepository userrepos;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        User user = userrepos.findByUsername(s.toLowerCase()); //usernames are case insensitive
        if (user == null)
        {
            throw new ResourceNotFoundException("Invalid username or password"); //don't say just invalid username. say both user/password.
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getAuthority());
    }
}
