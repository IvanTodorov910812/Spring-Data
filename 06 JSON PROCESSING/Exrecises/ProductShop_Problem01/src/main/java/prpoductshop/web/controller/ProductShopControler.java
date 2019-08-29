package prpoductshop.web.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import prpoductshop.domain.dtos.*;
import prpoductshop.service.CategoryService;
import prpoductshop.service.ProductService;
import prpoductshop.service.UserService;
import prpoductshop.util.FileUtil;

import java.io.*;

@Controller
public class ProductShopControler implements CommandLineRunner {

    private final String FILE_PATH_USER="D:\\000000 PROGRAMMING COURSE\\03_JAVA DB FUNDAMENTALS-JANUARY_2018\\JAVA DB FRAMEWORKS-HIBERNATE&SPRING DATA\\JAVA DB FRAMEWORKS - NOVEMBER 2018\\10 JSON PROCCESING\\Exercises\\ProductShop_Problem01\\src\\main\\resources\\files\\users.json";
    private final String FILE_PATH_PRODUCT="D:\\000000 PROGRAMMING COURSE\\03_JAVA DB FUNDAMENTALS-JANUARY_2018\\JAVA DB FRAMEWORKS-HIBERNATE&SPRING DATA\\JAVA DB FRAMEWORKS - NOVEMBER 2018\\10 JSON PROCCESING\\Exercises\\ProductShop_Problem01\\src\\main\\resources\\files\\products.json";
   private final String FILE_PATH_CATEGORY="D:\\000000 PROGRAMMING COURSE\\03_JAVA DB FUNDAMENTALS-JANUARY_2018\\JAVA DB FRAMEWORKS-HIBERNATE&SPRING DATA\\JAVA DB FRAMEWORKS - NOVEMBER 2018\\10 JSON PROCCESING\\Exercises\\ProductShop_Problem01\\src\\main\\resources\\files\\categories.json";
   private final String FILE_PATH_QUERY01="D:\\000000 PROGRAMMING COURSE\\03_JAVA DB FUNDAMENTALS-JANUARY_2018\\JAVA DB FRAMEWORKS-HIBERNATE&SPRING DATA\\JAVA DB FRAMEWORKS - NOVEMBER 2018\\10 JSON PROCCESING\\Exercises\\ProductShop_Problem01\\src\\main\\resources\\files\\query01.json" ;
    private final String FILE_PATH_QUERY02="D:\\000000 PROGRAMMING COURSE\\03_JAVA DB FUNDAMENTALS-JANUARY_2018\\JAVA DB FRAMEWORKS-HIBERNATE&SPRING DATA\\JAVA DB FRAMEWORKS - NOVEMBER 2018\\10 JSON PROCCESING\\Exercises\\ProductShop_Problem01\\src\\main\\resources\\files\\query02.json";
   private final String FILE_PATH_QUERY03="D:\\000000 PROGRAMMING COURSE\\03_JAVA DB FUNDAMENTALS-JANUARY_2018\\JAVA DB FRAMEWORKS-HIBERNATE&SPRING DATA\\JAVA DB FRAMEWORKS - NOVEMBER 2018\\10 JSON PROCCESING\\Exercises\\ProductShop_Problem01\\src\\main\\resources\\files\\query03.json";
    private final String FILE_PATH_QUERY04="D:\\000000 PROGRAMMING COURSE\\03_JAVA DB FUNDAMENTALS-JANUARY_2018\\JAVA DB FRAMEWORKS-HIBERNATE&SPRING DATA\\JAVA DB FRAMEWORKS - NOVEMBER 2018\\10 JSON PROCCESING\\Exercises\\ProductShop_Problem01\\src\\main\\resources\\files\\query04.json";
   private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productservice;
    private Gson gson;
    private FileUtil fileUtil;

    @Autowired
    public ProductShopControler(UserService userService, CategoryService categoryService, ProductService productservice, Gson gson, FileUtil fileUtil) throws IOException {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productservice = productservice;

        this.gson = gson;
        this.fileUtil = fileUtil;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedUsers();
        //this.seedCategories();
        //this.seedProducts();
        //this.queryProduct();
        //this.query02();
        //this.query03();
        this.query04();
    }

    private void query04() throws IOException {
        BasicDtosQuery04 basic=userService.query04();
        String jsonContent=this.gson.toJson(basic);
        File file=new File(FILE_PATH_QUERY04);
        OutputStream os = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        writer.write(jsonContent);
        writer.close();
    }

    private void query03() throws IOException {
        CategoryDtoQuery03[] categoriesDtos=this.categoryService.findCategoryDtoQuery03();
        String jsonContent=this.gson.toJson(categoriesDtos);
        File file=new File(FILE_PATH_QUERY03);
        OutputStream os = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        writer.write(jsonContent);
        writer.close();
    }

    private void query02() throws IOException {
        UserDtoQuery02[] usersDto =this.userService.query2();
        String jsonContent=this.gson.toJson(usersDto);
        File file=new File(FILE_PATH_QUERY02);
        OutputStream os = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        writer.write(jsonContent);
        writer.close();
    }


    private void queryProduct() throws IOException {
        ProductDtoInRange[] productsDto=this.productservice.findAllCategpryByPriceBetweenAndByerNull();

        String jsonContent=this.gson.toJson(productsDto);
        File file=new File(FILE_PATH_QUERY01);
        OutputStream os = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        writer.write(jsonContent);
        writer.close();
    }

    private void seedUsers() throws IOException {
        String content=this.fileUtil.readFile(FILE_PATH_USER);
     UserDto[] userDtos=this.gson.fromJson(content,UserDto[].class);
     this.userService.seedUsers(userDtos);
    }
    private void seedProducts() throws IOException {
        String content=this.fileUtil.readFile(FILE_PATH_PRODUCT);
        ProductDto[] productDtos=this.gson.fromJson(content,ProductDto[].class);
        this.productservice.seedProducts(productDtos);
    }
    private void seedCategories() throws IOException {
        String content = this.fileUtil.readFile(FILE_PATH_CATEGORY);
        CategoryDto[] categoryDtos = this.gson.fromJson(content, CategoryDto[].class);
        this.categoryService.seedCatgeories(categoryDtos);
    }
}
