package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.RacerImportDTO;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class RacerServiceImpl implements RacerService {

    private final static String RACERS_JSON_FILE_PATH =
            System.getProperty("user.dir") + "/src/main/resources/files/racers.json";

    private final RacerRepository racerRepository;
    private final FileUtil fileUtil;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository, FileUtil fileUtil, TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean racersAreImported() {

        return this.racerRepository.count() > 0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {

        return this.fileUtil.readFile(RACERS_JSON_FILE_PATH);
    }

    @Override
    public String importRacers(String racersFileContent) {

        StringBuilder importResult = new StringBuilder();

        Arrays.stream(this.gson.fromJson(racersFileContent, RacerImportDTO[].class)).forEach(racerImportDTO -> {
            Racer racerEntity = this.racerRepository.findByName(racerImportDTO.getName()).orElse(null);
            if(racerEntity != null) {
                importResult.append(Constants.DUPLICATE_DATA_MESSAGE)
                        .append(System.lineSeparator());

                return;
            }

            Town townEntity = this.townRepository
                    .findByName(racerImportDTO.getHomeTown())
                    .orElse(null);
            if(!this.validationUtil.isValid(racerImportDTO) || townEntity == null) {
                importResult.append(Constants.DUPLICATE_DATA_MESSAGE)
                        .append(System.lineSeparator());
                return;
            }

            racerEntity = this.modelMapper.map(racerImportDTO, Racer.class);
            racerEntity.setHomeTown(townEntity);
            this.racerRepository.saveAndFlush(racerEntity);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    racerEntity.getClass().getSimpleName(),
                    racerEntity.getName()))
                    .append(System.lineSeparator());

        });
        return importResult.toString().trim();
    }

    @Override
    public String exportRacingCars() {
        return null;
    }
}
