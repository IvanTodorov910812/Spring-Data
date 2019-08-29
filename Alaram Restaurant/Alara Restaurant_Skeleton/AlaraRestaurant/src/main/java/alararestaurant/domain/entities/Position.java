package alararestaurant.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name="positions")
public class Position extends BaseEntity{
    /*
    •	id – integer, Primary Key
•	name – text with min length 3 and max length 30 (required, unique)
•	employees – Collection of type Employee

     */
    private String name;
    private List<Employee> employees;

    public Position() {
    }

    @Column(name="name",nullable = false,unique = true)
    @Size(min=3, max=30)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "position",targetEntity = Employee.class)
    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
