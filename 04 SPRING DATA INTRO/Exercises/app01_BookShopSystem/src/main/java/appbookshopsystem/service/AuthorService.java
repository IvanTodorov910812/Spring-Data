package appbookshopsystem.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


public interface AuthorService {
    void seedAuthor() throws IOException;
    List<String> authorOrderByBooksNumber();
}
