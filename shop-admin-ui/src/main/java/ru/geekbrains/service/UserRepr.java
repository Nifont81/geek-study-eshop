package ru.geekbrains.service;


import ru.geekbrains.persist.model.Role;
import ru.geekbrains.persist.model.User;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// DTO
public class UserRepr {

    private Long id;

//    @NotEmpty
    private String login;

//    @NotEmpty
    private String password;

//    @JsonIgnore
//    @NotEmpty
    private String matchingPassword;

//    @Email
    private String email;

    private Integer age;

    private Set<Role> roles;

    public UserRepr() {
    }

    public UserRepr(String login) {
        this.login = login;
    }

    public UserRepr(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.roles = new HashSet<>(user.getRoles());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRepr userRepr = (UserRepr) o;
        return id.equals(userRepr.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
