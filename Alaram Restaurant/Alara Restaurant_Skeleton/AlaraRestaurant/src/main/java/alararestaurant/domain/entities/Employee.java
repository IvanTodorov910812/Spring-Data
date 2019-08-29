package alararestaurant.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name="employees")
public class Employee extends BaseEntity {
    /*
    •	id – integer, Primary Key
•	name – text with min length 3 and max length 30 (required)
•	age – integer in the range [15, 80] (required)
•	position – the employee’s position (required)
•	orders – the orders the employee has processed

     */
    private String name;
    private Integer age;
    private Position position;
    private List<Order> orders;

    public Employee() {
    }

    @Column(name="name",nullable = false)
    @Size(min=3,max=30)
    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Column(name="age",nullable = false)
    @Min(15)
    @Max(80)
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @ManyToOne
    @JoinColumn(name="position_id",referencedColumnName = "id")
    @NotNull
    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @OneToMany(mappedBy = "employee",targetEntity = Order.class)
    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
