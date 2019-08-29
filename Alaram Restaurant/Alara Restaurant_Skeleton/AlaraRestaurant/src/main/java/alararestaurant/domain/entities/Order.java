package alararestaurant.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name="orders")
public class Order extends BaseEntity{
    /*
    •	id – integer, Primary Key
•	customer – text (required)
•	dateTime – date and time of the order (required)
•	type – OrderType enumeration with possible values: “ForHere, ToGo (default: ForHere)” (required)
•	employee – The employee who will process the order (required)
•	orderItems – collection of type OrderItem

     */
    private String customer;
    private LocalDate date;
    private OrderType orderType;
    private Employee employee;
    private List<OrderItem> orderItems;

    public Order() {
    }

    @Column(name="customer",columnDefinition = "TEXT",nullable = false)
    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Column(name="date_time",columnDefinition = "DATETIME",nullable = false)
    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="type",columnDefinition = "ENUM('ForHere','ToGo') default 'ForHere'",nullable = false)
    public OrderType getOrderType() {
        return this.orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    @ManyToOne
    @JoinColumn(name="employee_id",referencedColumnName = "id")
    @NotNull
    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @OneToMany(mappedBy = "order",targetEntity = OrderItem.class)
    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
