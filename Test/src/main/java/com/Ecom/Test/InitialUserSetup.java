package com.Ecom.Test;

import com.Ecom.Test.Model.Roles;
import com.Ecom.Test.Model.User;
import com.Ecom.Test.Repository.RoleRepository;
import com.Ecom.Test.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class InitialUserSetup {

    @Value("${email}")
    private String email;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Roles roles = createRole("ROLE_ADMIN");
        User user = new User();
        user.setUserName("unnie");
        user.setEmail("admin@gmail.com");
        user.setPassword(bCryptPasswordEncoder.encode("123"));
        userRepository.save(user);
        Collection<Roles> rolesCollection = new ArrayList<>();
        rolesCollection.add(roles);
        user.setRoles(rolesCollection);
        userRepository.save(user);

    }

    public Roles createRole(String name){
        Roles roles = roleRepository.findByName(name);
        if(roles == null){
            roles = new Roles();
            roles.setName(name);
            roleRepository.save(roles);
        }
        return roles;
    }
}