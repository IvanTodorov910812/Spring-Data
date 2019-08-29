package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.DistrictImportDTO;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final static String DISTRICTS_JSON_FILE_PATH =
            System.getProperty("user.dir") + "/src/main/resources/files/districts.json";

    private final DistrictRepository districtRepository;
    private final FileUtil fileUtil;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, FileUtil fileUtil, TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.fileUtil = fileUtil;
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count() > 0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
        return this.fileUtil.readFile(DISTRICTS_JSON_FILE_PATH);
    }

    @Override
    public String importDistricts(String districtsFileContent) {

        StringBuilder importResult = new StringBuilder();
        DistrictImportDTO[] districtImportDTOS =
                this.gson.fromJson(districtsFileContent, DistrictImportDTO[].class);

        Arrays.stream(districtImportDTOS).forEach(districtImportDTO -> {
            District districtEntity = this.districtRepository.findByName(districtImportDTO.getName()).orElse(null);
            if(districtEntity != null){
                importResult.append(Constants.DUPLICATE_DATA_MESSAGE)
                        .append(System.lineSeparator());

                return;
            }

            Town townEntity = this.townRepository
                    .findByName(districtImportDTO.getTownName())
                    .orElse(null);

            if(!this.validationUtil.isValid(districtImportDTO) || townEntity == null) {
                importResult.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());

                return;
            }

            districtEntity = this.modelMapper.map(districtImportDTO, District.class);
            districtEntity.setTown(townEntity);
            this.districtRepository.saveAndFlush(districtEntity);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    districtEntity.getClass().getSimpleName(),
                    districtEntity.getName()))
                    .append(System.lineSeparator());
        });
        return importResult.toString().trim();
    }
}
