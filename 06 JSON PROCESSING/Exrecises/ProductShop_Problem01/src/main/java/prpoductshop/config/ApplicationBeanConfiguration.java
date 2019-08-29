package prpoductshop.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import prpoductshop.util.FileUtil;
import prpoductshop.util.FileUtilImpl;
import prpoductshop.util.ValidatorUtil;
import prpoductshop.util.ValidatorUtilImpl;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public Gson gson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }
    @Bean
    public ModelMapper modelMapper(){
       return  new ModelMapper();
    }
    @Bean
    public FileUtil fileUtil(){
        return  new FileUtilImpl();
    }
    @Bean
    public ValidatorUtil validatorUtil() {
        return new ValidatorUtilImpl();
    }
}

