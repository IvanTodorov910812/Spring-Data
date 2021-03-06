package prpoductshop.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;


@Entity(name="products")
public class Product extends BaseEntity {
    private String name;
    private BigDecimal price;
    private User seller;
    private User buyer;
    private List<Category> categories;

    public Product() {
    }

    @Column(name="name",nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="price",nullable = false)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name="seller_id",referencedColumnName = "id")
    public User getSeller() {
        return this.seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @ManyToOne
    @JoinColumn(name="buyer_id",referencedColumnName = "id")
    public User getBuyer() {
        return this.buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @ManyToMany
    @JoinTable(name="category_products",
            joinColumns = @JoinColumn(name="product_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="category_id",referencedColumnName = "id"))
    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
