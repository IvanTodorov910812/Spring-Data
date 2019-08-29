package app05_BillsPaymentSystem.entities;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    //first name, last name, email, password, billing details
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<BaseBillingDetail> details;


    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="password")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user",targetEntity = BaseBillingDetail.class)
    public Set<BaseBillingDetail> getDetails() {
        return this.details;
    }

    public void setDetails(Set<BaseBillingDetail> details) {
        this.details = details;
    }
}
