package appbookshopsystem.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface BookService {
    void seedBook() throws IOException;
    List<String> booksAfterLocalDate();
    List<String> booksBeforeLocalDate();
    List<String> booksByAuthor();
}
