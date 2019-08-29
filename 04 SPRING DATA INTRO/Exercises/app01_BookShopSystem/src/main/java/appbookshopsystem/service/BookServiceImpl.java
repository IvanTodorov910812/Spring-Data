package appbookshopsystem.service;

import appbookshopsystem.domain.entities.*;
import appbookshopsystem.repository.AuthorRepository;
import appbookshopsystem.repository.BookRepository;
import appbookshopsystem.repository.CategoryRepository;
import appbookshopsystem.util.FileUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
 private final String BOOKS_PATH_FILE="D:\\000000 PROGRAMMING COURSE\\03_JAVA DB FUNDAMENTALS-JANUARY_2018\\JAVA DB FRAMEWORKS-HIBERNATE&SPRING DATA\\JAVA DB FRAMEWORKS - NOVEMBER 2018\\07 SPRING DATA INTRO\\Exercises\\app01_BookShopSystem\\src\\main\\resources\\files\\books.txt";
 private final BookRepository bookRepository;
 private final AuthorRepository authorRepository;
 private final CategoryRepository categoryRepository;
 private final FileUtil fileUtil;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBook() throws IOException {
        String[] readBooksFile=this.fileUtil.readFile(BOOKS_PATH_FILE);
        if(this.bookRepository.count()!=0){
            return;
        }

        for (String booksLine : readBooksFile) {
            String[] lineBook=booksLine.split("\\s+");
            Book book=new Book();
            Author author = this.getRandomAuthor();
            book.setAuthor(author);
            EditionType editionType=EditionType.values()[Integer.parseInt(lineBook[0])];
            book.setEditionType(editionType);
            LocalDate releaseDate= LocalDate.parse(lineBook[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);
            int copies = Integer.parseInt(lineBook[2]);
            book.setCopies(copies);
            BigDecimal price = new BigDecimal(lineBook[3]);
            book.setPrice(price);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(lineBook[4])];
            book.setAgeRestriction(ageRestriction);
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < lineBook.length; i++) {
                titleBuilder.append(lineBook[i]).append(" ");
            }
            String title=titleBuilder.toString().trim();
            book.setTitle(title);
            Set<Category> categories=this.getSetCategory();
            book.setCategories(categories);
            this.bookRepository.saveAndFlush(book);

        }



    }

    @Override
    public List<String> booksAfterLocalDate() {
        List<Book> books=this.bookRepository.findAllByReleaseDateAfter(LocalDate.parse("2000-12-31"));
        return books.stream().map(x->x.getTitle()).collect(Collectors.toList());
    }
    @Override
    public List<String> booksBeforeLocalDate() {
        List<Book> books=this.bookRepository.findAllByReleaseDateBefore(LocalDate.parse("1990-01-01"));
        return books.stream().map(x->String.format("%s %s",x.getAuthor().getFirstName(),x.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> booksByAuthor() {
       Author author=this.authorRepository.findAll().stream().filter(x->x.getFirstName().equals("George") &&
                x.getLastName().equals("Powell")).findFirst().orElse(null);
        List<Book> books=this.bookRepository.findAllByAuthorOrderByReleaseDateDescTitleAsc(author);
        return books.stream().map(x->String.format("%s %s %d",x.getTitle(),x.getReleaseDate(),x.getCopies())).collect(Collectors.toList());
    }

    private Author getRandomAuthor() {
        Random random= new Random();
        int randomIndex=random.nextInt((int) (this.authorRepository.count() - 1))+1;
        return this.authorRepository.findById(randomIndex).orElse(null);
    }
    private Category getRandomCategory() {
        Random random= new Random();
        int randomIndex=random.nextInt((int) (this.categoryRepository.count() - 1))+1;
        return this.categoryRepository.findById(randomIndex).orElse(null);
    }
    private Set<Category> getSetCategory() {
        Set<Category> categories= new LinkedHashSet<>();
        Random random = new Random();
        int numberCategory=random.nextInt((int) (this.categoryRepository.count()-1))+1;
        for (int i = 0; i <numberCategory ; i++) {
            Category category=this.getRandomCategory();
            categories.add(category);
    }

        return categories;
    }
}
