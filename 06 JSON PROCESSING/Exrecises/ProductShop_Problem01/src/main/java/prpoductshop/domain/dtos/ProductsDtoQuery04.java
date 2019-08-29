package prpoductshop.domain.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ProductsDtoQuery04 {
    @Expose
    private Integer count;
    @Expose
    private List<ProductDtoQuery04> products;

    public ProductsDtoQuery04() {
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductDtoQuery04> getSoldProducts() {
        return this.products;
    }

    public void setSoldProducts(List<ProductDtoQuery04> soldProducts) {
        this.products = soldProducts;
    }
}
