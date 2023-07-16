package com.Ecom.Test.Utils;

import com.Ecom.Test.Model.Roles;
import com.Ecom.Test.Model.User;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class Utils {

    public static List<String> getAuthorities(User user) {
        Collection<Roles> roles = user.getRoles();
        List<String> authorities = new ArrayList<>();
        if(roles == null)
            return authorities;
        roles.forEach((role) -> {
            authorities.add(role.getName());
        });
        return authorities;
    }
}