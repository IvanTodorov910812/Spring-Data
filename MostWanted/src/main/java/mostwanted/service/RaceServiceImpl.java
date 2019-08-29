package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.races.RaceImportRootDTO;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RaceServiceImpl implements RaceService {

    private final static String RACES_XML_FILE_PATH =
            System.getProperty("user.dir") + "/src/main/resources/files/races.xml";

    private final RaceRepository raceRepository;
    private final FileUtil fileUtil;
    private final DistrictRepository districtRepository;
    private final RaceEntryRepository raceEntryRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public RaceServiceImpl(RaceEntryRepository raceEntryRepository, RaceRepository raceRepository, FileUtil fileUtil, DistrictRepository districtRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.raceRepository = raceRepository;
        this.fileUtil = fileUtil;
        this.districtRepository = districtRepository;
        this.raceEntryRepository = raceEntryRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public Boolean racesAreImported() {
        return this.raceRepository.count() > 0;
    }

    @Override
    public String readRacesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACES_XML_FILE_PATH);
    }

    @Override
    public String importRaces() {
            return "";
    }
}

//        StringBuilder importResult = new StringBuilder();
//
//        RaceImportRootDTO raceImportRootDTO = this.xmlParser
//                .parseXml(RaceImportRootDTO.class, RACES_XML_FILE_PATH);

//        Arrays.stream(raceImportRootDTO.getRaceImportDTOS()).forEach(raceImportDTO -> {
//            District districtEntity = this.districtRepository
//                    .findByName(raceImportDTO.getDistrict())
//                    .orElse(null);
//            if(!this.validationUtil.isValid(raceImportDTO) || districtEntity == null) {
//                importResult.append(Constants.INCORRECT_DATA_MESSAGE)
//                        .append(System.lineSeparator());
//
//                return;
//            }
//
//            Race raceEntity = this.modelMapper.map(raceImportDTO, Race.class);
//            raceEntity.setDistrict(districtEntity);
//
//            List<RaceEntry> raceEntryEntities = new ArrayList<>();
        //TODO
//        });
//        return importResult.toString().trim();
//    }
//}
