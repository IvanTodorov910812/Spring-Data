package alararestaurant.service;

import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
 private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String exportCategoriesByCountOfItems() {
        StringBuilder sb=new StringBuilder();
        List<Category> categories=this.categoryRepository.findByCountItem();
        for (Category category : categories) {
            sb.append("Çategory: ").append(category.getName()).append(System.lineSeparator());
            sb.append(System.lineSeparator());
            for (Item item : category.getItems()) {
                sb.append(String.format("--- Item Name: %s",item.getName())).append(System.lineSeparator());
                sb.append(String.format("--- Item Price: %s",item.getPrice())).append(System.lineSeparator());
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
