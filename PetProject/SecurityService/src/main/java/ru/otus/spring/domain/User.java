package ru.otus.spring.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.util.List;

@Entity
@Getter
@Setter//@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private String password;

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Role> roles;
}
