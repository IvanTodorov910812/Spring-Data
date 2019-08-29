package alararestaurant.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity(name="order_items")
public class OrderItem extends BaseEntity{
    /*
    •	id – integer, Primary Key
•	order – the item’s order (required)
•	item – the order’s item (required)
•	quantity – the quantity of the item in the order (required, non-negative and non-zero)

     */
    private Order order;
    private Item item;
    private Integer quantity;

    public OrderItem() {
    }

    @ManyToOne
    @JoinColumn(name="order_id",referencedColumnName = "id")
    @NotNull
    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name="item_id",referencedColumnName = "id")
    @NotNull
    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Column(name="quantity",nullable = false)
    @Min(1)
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}