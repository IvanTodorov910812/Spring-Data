package prpoductshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import prpoductshop.domain.dtos.CategoryDto;
import prpoductshop.domain.dtos.CategoryDtoQuery03;
import prpoductshop.domain.entities.Category;
import prpoductshop.repository.CategoryRepository;
import prpoductshop.util.ValidatorUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class CatgeoryServiceImpl implements CategoryService {
    private final ModelMapper modelMapper;
    private final ValidatorUtil validationUtil;
    private final CategoryRepository categoryRepository;

    public CatgeoryServiceImpl(ModelMapper modelMapper, ValidatorUtil validationUtil, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCatgeories(CategoryDto[] categoryDtos) {
        for (CategoryDto categoryDto : categoryDtos) {
            if(this.validationUtil.isValid(categoryDto)) {
                Category category = this.modelMapper.map(categoryDto,Category.class);
                this.categoryRepository.saveAndFlush(category);
            }
        }
    }

    @Override
    public CategoryDtoQuery03[] findCategoryDtoQuery03() {
        List<Object[]> categoriesCount=this.categoryRepository.findCategoryByuerIsNotNull();
        List<Object[]> categories=this.categoryRepository.findAllOrderByNumberProducts();
        CategoryDtoQuery03[] categoriesDto=new CategoryDtoQuery03[categories.size()];
        for (int i = 0; i <categories.size() ; i++) {
            CategoryDtoQuery03 categoryDtoQuery03=new CategoryDtoQuery03();
            String name=String.valueOf(categories.get(i)[0]);
            categoryDtoQuery03.setName(name);
            categoryDtoQuery03.setProductsCount(Integer.parseInt(String.valueOf(categories.get(i)[1])));
            categoryDtoQuery03.setAveragePrice(BigDecimal.valueOf(Double.parseDouble(String.valueOf(categories.get(i)[2]))));
            categoryDtoQuery03.setTotalRevenue(BigDecimal.valueOf(Double.parseDouble(String.valueOf(categoriesCount.get(i)[1]))).setScale(2,RoundingMode.CEILING));
            categoriesDto[i]=categoryDtoQuery03;
        }
        System.out.println();



        return categoriesDto;
    }
}
