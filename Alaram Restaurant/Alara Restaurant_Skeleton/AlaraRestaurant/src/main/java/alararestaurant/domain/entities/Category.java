package alararestaurant.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name="categories")
public class Category extends BaseEntity{
    /*
    •	id – integer, Primary Key
•	name – text with min length 3 and max length 30 (required)
•	items – collection of type Item

     */
    private String name;
    private List<Item> items;

    public Category() {
    }

    @Column(name="name",nullable = false)
    @Size(min=3, max=30)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy="category",targetEntity = Item.class)
    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
