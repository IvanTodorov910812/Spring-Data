package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.TownImportDTO;
import mostwanted.domain.entities.Town;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class TownServiceImpl implements TownService {

    private final static String TOWNS_JSON_FILE_PATH =
            System.getProperty("user.dir") + "/src/main/resources/files/towns.json";
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean townsAreImported() {
        return  this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return this.fileUtil.readFile(TOWNS_JSON_FILE_PATH);
    }

    @Override
    public String importTowns(String townsFileContent) {
        StringBuilder importResult = new StringBuilder();
        TownImportDTO[] townImportDTOS =
                this.gson.fromJson(townsFileContent, TownImportDTO[].class);

        Arrays.stream(townImportDTOS).forEach(townImportDTO -> {
            Town townEntity =
                    this.townRepository.findByName(townImportDTO.getName()).orElse(null);
            if(townEntity != null){
                importResult.append(Constants.DUPLICATE_DATA_MESSAGE)
                        .append(System.lineSeparator());

                return;
            }

            if(!this.validationUtil.isValid(townImportDTO)){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());

                return;
            }

            townEntity = this.modelMapper.map(townImportDTO, Town.class);
            this.townRepository.saveAndFlush(townEntity);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    townEntity.getClass().getSimpleName(),
                    townEntity.getName()))
                    .append(System.lineSeparator());
        });

        return importResult.toString().trim();
    }

    @Override
    public String exportRacingTowns() {
        return null;
    }
}
