package alararestaurant.service;

import alararestaurant.domain.dtos.CategoryImportDto;
import alararestaurant.domain.dtos.ItemImportDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ItemServiceImpl implements ItemService {
    private final String FILE_PATH_ITEMS = System.getProperty("user.dir") + "/src/main/resources/files/items.json";
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean itemsAreImported() {

        return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() throws IOException {
        return this.fileUtil.readFile(FILE_PATH_ITEMS);
    }

    @Override
    public String importItems(String items) {
        StringBuilder sb = new StringBuilder();
        ItemImportDto[] itemImportDtos = this.gson.fromJson(items, ItemImportDto[].class);
        for (ItemImportDto itemImportDto : itemImportDtos) {
            Item item=this.itemRepository.findByName(itemImportDto.getName()).orElse(null);
            Category category = this.categoryRepository.findByName(itemImportDto.getCategory()).orElse(null);
            CategoryImportDto categoryImportDto = new CategoryImportDto();
            categoryImportDto.setName(itemImportDto.getCategory());
            if(!this.validationUtil.isValid(itemImportDto) || !this.validationUtil.isValid(categoryImportDto)){
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }
            if(item!=null){
                continue;
            }
            item = this.modelMapper.map(itemImportDto, Item.class);
            if(category==null){
                category=this.modelMapper.map(categoryImportDto,Category.class);
                this.categoryRepository.saveAndFlush(category);
            }
            item.setCategory(category);
            this.itemRepository.saveAndFlush(item);
            sb.append(String.format("Record %s successfully imported.",item.getName())).append(System.lineSeparator());

        }
        return sb.toString().trim();
    }
}
