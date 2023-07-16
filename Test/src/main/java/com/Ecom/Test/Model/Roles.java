package com.Ecom.Test.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    private static final long serialVersionUID = 2329281802878721981L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false,length = 20)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

}
