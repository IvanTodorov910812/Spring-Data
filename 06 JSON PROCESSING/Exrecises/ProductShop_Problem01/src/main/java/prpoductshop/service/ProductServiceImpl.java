package prpoductshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prpoductshop.domain.dtos.ProductDto;
import prpoductshop.domain.dtos.ProductDtoInRange;
import prpoductshop.domain.entities.Category;
import prpoductshop.domain.entities.Product;
import prpoductshop.domain.entities.User;
import prpoductshop.repository.CategoryRepository;
import prpoductshop.repository.ProductRepository;
import prpoductshop.repository.UserRepository;
import prpoductshop.util.ValidatorUtil;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }
   @Override
    public void seedProducts(ProductDto[] productDtos) {
       List<Product> products= new ArrayList<>();
        for (ProductDto productDto : productDtos) {
            if (validatorUtil.isValid(productDto)) {
                Product product = modelMapper.map(productDto, Product.class);
                products.add(product);
            }
        }
            this.setCategory(products);
            this.setSeller(products);
            this.setBuyer(products);
       for (Product product : products) {
           this.productRepository.saveAndFlush(product);
       }
    }

    @Override
    public ProductDtoInRange[] findAllCategpryByPriceBetweenAndByerNull() {
        List<Product> products=this.productRepository.
               findAllByPriceBetweenAndBuyerNullOrderByPrice(new BigDecimal(500),new BigDecimal(1000));
        ProductDtoInRange[]  productDto=modelMapper.map(products,ProductDtoInRange[].class);

        for (int i = 0; i <products.size() ; i++) {
            productDto[i].setFullName(String.format("%s %s",products.get(i).getSeller().getFirstName(),
                    products.get(i).getSeller().getLastName()));
        }
        return productDto;
    }

    private void setCategory(List<Product> products) {
        Random random=new Random();

        for (Product product : products) {
            List<Category> categories=new ArrayList<>();
            int countCategory=random.nextInt((int)this.categoryRepository.count() - 1) + 1;
            for (int i = 0; i <countCategory ; i++) {
                int idCategory=random.nextInt((int)this.categoryRepository.count() - 1) +1;
                Category category = this.categoryRepository.findById((long)idCategory).orElse(null);
                categories.add(category);
            }
            product.setCategories(categories);
        }


    }

    private void setBuyer(List<Product> products) {
        Random random=new Random();
        int countByerNull=random.nextInt((int)products.size());
        long lenght=products.size()-countByerNull+1;
        for (int i = 0; i <lenght ; i++) {
            long buyerId=this.getRandom();
            User user=this.userRepository.findById((long) buyerId).orElse(null);
            products.get(i).setBuyer(user);
        }
    }
    private void setSeller(List<Product> products) {

        for (int i = 0; i <products.size() ; i++) {
            int sellerId=this.getRandom();
            User user=this.userRepository.findById((long) sellerId).orElse(null);
            products.get(i).setSeller(user);
        }
    }
    private int getRandom() {
        Random random=new Random();
        return random.nextInt((int)this.userRepository.count() - 1) + 1;
    }
}
