package appbookshopsystem.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name="authors")
public class Author extends BaseEntity {
    private String firstName;
    private String lastName;
    private Set<Book> books;

    public Author() {
       this.books=new HashSet<>();
    }

    @Column(name="first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="last_name",nullable=false)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToMany(mappedBy = "author", targetEntity = Book.class,fetch = FetchType.EAGER)
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }


}
