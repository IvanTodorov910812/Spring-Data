package prpoductshop.service;

import org.springframework.stereotype.Service;
import prpoductshop.domain.dtos.CategoryDto;
import prpoductshop.domain.dtos.CategoryDtoQuery03;

import java.util.List;

@Service
public interface CategoryService {
    void seedCatgeories(CategoryDto[] categoryDtos);
    public CategoryDtoQuery03[] findCategoryDtoQuery03();
}
