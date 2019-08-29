package prpoductshop.util;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface FileUtil{
    public String readFile(String path) throws IOException;

}
