package ru.bramblehorse.cms.model.security;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 18.11.13
 * Time: 1:15
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "admin_accounts")
public class Account {

    @Id
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_password")
    private String userPassword;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "accounts_roles",
    joinColumns = @JoinColumn(name = "user_name"),
    inverseJoinColumns = @JoinColumn(name = "role_name"))
    private Set<TomcatRole> roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Set<TomcatRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<TomcatRole> roles) {
        this.roles = roles;
    }
}
