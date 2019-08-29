package prpoductshop.service;

import prpoductshop.domain.dtos.ProductDto;
import prpoductshop.domain.dtos.ProductDtoInRange;

import java.util.List;

public interface ProductService {
    void seedProducts(ProductDto[] productDtos);
    ProductDtoInRange[] findAllCategpryByPriceBetweenAndByerNull();

}
