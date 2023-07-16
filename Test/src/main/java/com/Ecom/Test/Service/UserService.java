package com.Ecom.Test.Service;

import com.Ecom.Test.IO.Request.UserRequest;
import com.Ecom.Test.IO.Response.UserResponse;
import com.Ecom.Test.Model.User;
import com.Ecom.Test.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserService  implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserResponse createUser(UserRequest userRequest){
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        return convertToResponse(userRepository.save(user));
    }

    public UserResponse getUserById(Integer id){
        return convertToResponse(userRepository.getById(id));
    }
    public User getUser(String email) {
        User client = userRepository.getUserByEmail(email);
        User returnValue = new User();
        BeanUtils.copyProperties(client,returnValue);
        return returnValue;
    }
    public UserResponse convertToResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserName(user.getUserName());
        userResponse.setEmail(user.getEmail());
        userResponse.setUserId(user.getUserId());
        return userResponse;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(username);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),
                true,true,true,true,new ArrayList<>());
    }
}