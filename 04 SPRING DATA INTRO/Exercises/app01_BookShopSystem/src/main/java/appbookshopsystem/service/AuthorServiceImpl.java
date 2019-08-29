package appbookshopsystem.service;

import appbookshopsystem.domain.entities.Author;
import appbookshopsystem.repository.AuthorRepository;
import appbookshopsystem.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHORS_FILE_PATH="D:\\000000 PROGRAMMING COURSE\\03_JAVA DB FUNDAMENTALS-JANUARY_2018\\JAVA DB FRAMEWORKS-HIBERNATE&SPRING DATA\\JAVA DB FRAMEWORKS - NOVEMBER 2018\\07 SPRING DATA INTRO\\Exercises\\app01_BookShopSystem\\src\\main\\resources\\files\\authors.txt";
    private final AuthorRepository authorRepositpry;
    private final FileUtil fileUtil;

    public AuthorServiceImpl(AuthorRepository authorRepositpry, FileUtil fileUtil) {
        this.authorRepositpry = authorRepositpry;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthor() throws IOException {

        String[] readAuthors=this.fileUtil.readFile(AUTHORS_FILE_PATH);

        if(this.authorRepositpry.count()!=0){
            return;
        }
        for (String readAuthor : readAuthors) {
            String[] names=readAuthor.split("\\s+");

            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);
            this.authorRepositpry.saveAndFlush(author);
        }

    }

    @Override
    public List<String> authorOrderByBooksNumber() {
        return this.authorRepositpry.findAll().stream().sorted((x, y) -> Integer.compare(y.getBooks().size(),
                x.getBooks().size())).map(x -> String.format("%s %s", x.getFirstName(), x.getLastName()))
                .collect(Collectors.toList());
    }



}
