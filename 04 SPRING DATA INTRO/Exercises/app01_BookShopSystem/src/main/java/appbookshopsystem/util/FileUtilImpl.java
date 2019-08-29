package appbookshopsystem.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtilImpl implements FileUtil {

    @Override
    public String[] readFile(String filePath) throws IOException {
         File file= new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines= new ArrayList<>();
       String line;
        while((line=reader.readLine())!=null){
             lines.add(line);

     }
        return lines.stream().filter(x->!("").equals(x)).toArray(String[]::new);
    }
}
