package prpoductshop.util;

import java.io.*;

public class FileUtilImpl implements FileUtil {
    @Override
    public String readFile(String path) throws IOException {
        File file= new File(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line=null;
        StringBuilder sb = new StringBuilder();
        while((line=reader.readLine())!=null){
            sb.append(line);
        }
        return sb.toString();
    }
}
