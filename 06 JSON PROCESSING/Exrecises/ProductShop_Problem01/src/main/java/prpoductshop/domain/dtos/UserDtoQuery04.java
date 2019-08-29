package prpoductshop.domain.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UserDtoQuery04 {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;
    @Expose
    private ProductsDtoQuery04 soldProducts;

    public UserDtoQuery04() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProductsDtoQuery04 getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(ProductsDtoQuery04 soldProducts) {
        this.soldProducts = soldProducts;
    }
}
