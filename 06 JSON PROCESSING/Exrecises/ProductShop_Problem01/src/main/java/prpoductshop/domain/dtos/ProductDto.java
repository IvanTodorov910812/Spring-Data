package prpoductshop.domain.dtos;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import com.google.gson.annotations.Expose;
public class ProductDto{
    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public ProductDto() {
    }

    @NotNull
    @Size(min=3)
    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }
    @NotNull
    @Positive
    @DecimalMin(value="0.01")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
