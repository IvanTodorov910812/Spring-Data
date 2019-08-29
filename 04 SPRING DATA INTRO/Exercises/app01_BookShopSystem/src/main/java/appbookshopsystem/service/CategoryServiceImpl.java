package appbookshopsystem.service;

import appbookshopsystem.domain.entities.Category;
import appbookshopsystem.repository.CategoryRepository;
import appbookshopsystem.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String CATEGORIES_FILE_PATH="D:\\000000 PROGRAMMING COURSE\\03_JAVA DB FUNDAMENTALS-JANUARY_2018\\JAVA DB FRAMEWORKS-HIBERNATE&SPRING DATA\\JAVA DB FRAMEWORKS - NOVEMBER 2018\\07 SPRING DATA INTRO\\Exercises\\app01_BookShopSystem\\src\\main\\resources\\files\\categories.txt";
    private final CategoryRepository categoryRepository;
    private final FileUtil finalUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil finalUtil) {
        this.categoryRepository = categoryRepository;
        this.finalUtil = finalUtil;
    }

    @Override
    public void seedCategory() throws IOException {
        String[] readCategory= this.finalUtil.readFile(CATEGORIES_FILE_PATH);
        if(this.categoryRepository.count()!=0){
            return;
        }
        for (String line : readCategory) {
            Category category = new Category();
            category.setName(line);
            this.categoryRepository.saveAndFlush(category);

        }
    }
}
