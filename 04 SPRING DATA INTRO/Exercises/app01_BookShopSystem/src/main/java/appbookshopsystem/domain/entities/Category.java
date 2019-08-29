package appbookshopsystem.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name="categories")
public class Category extends BaseEntity {
    private String name;
    private Set<Book> books;
    public Category() {
    }

    @Column(name="name",nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
