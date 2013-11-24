package ru.bramblehorse.cms.model.security;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 19.11.13
 * Time: 0:21
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="tomcat_roles")
public class TomcatRole {
    @Id
    @Column(name = "role_name")
    private String roleName;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "accounts_roles",
            joinColumns = @JoinColumn(name = "role_name"),
            inverseJoinColumns = @JoinColumn(name = "user_name"))
    private Set<Account> accounts;

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
