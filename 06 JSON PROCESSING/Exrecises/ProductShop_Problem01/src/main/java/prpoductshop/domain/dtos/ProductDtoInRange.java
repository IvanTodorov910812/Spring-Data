package prpoductshop.domain.dtos;

import java.math.BigDecimal;
import com.google.gson.annotations.Expose;
public class ProductDtoInRange {
    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private String fullName;

    public ProductDtoInRange() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
