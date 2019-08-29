package appbookshopsystem.controller;

import appbookshopsystem.service.AuthorService;
import appbookshopsystem.service.BookService;
import appbookshopsystem.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookShopSystemController implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    public BookShopSystemController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.authorService.seedAuthor();
        this.categoryService.seedCategory();
        this.bookService.seedBook();
        //this.printBooksAfterYear();
        //this.printAuthorBeforeYear();
        //this.printAllAuthorsByNumberBooks();
        this.printAllBooksWithAuthor();
    }

    private void printAllBooksWithAuthor() {
        List<String> books=this.bookService.booksByAuthor();
        System.out.println(String.join(System.lineSeparator(), books));
    }

    private void printAllAuthorsByNumberBooks() {
        List<String> authors=this.authorService.authorOrderByBooksNumber();
        System.out.println(String.join(System.lineSeparator(), authors));
    }

    private void printAuthorBeforeYear () {
            List<String> books = this.bookService.booksAfterLocalDate();
            System.out.println(String.join(System.lineSeparator(), books));
        }

        private void printBooksAfterYear () {
            List<String> books = this.bookService.booksAfterLocalDate();
            System.out.println(String.join(System.lineSeparator(), books));
        }

}
