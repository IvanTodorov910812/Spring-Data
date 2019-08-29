package prpoductshop.domain.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UserDtoQuery02 {
    /*
    "firstName": "Carl",
    "lastName": "Daniels",
    "soldProducts":
     */
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private List<ProductDtoQuery02> soldProducts;

    public UserDtoQuery02() {
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

    public List<ProductDtoQuery02> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(List<ProductDtoQuery02> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
