package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.raceentries.RaceEntryImportRootDTO;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;

@Service
public class RaceEntryServiceImpl implements  RaceEntryService {

    private final static String RACE_ENTRIES_XML_FILE_PATH =
            System.getProperty("user.dir") + "/src/main/resources/files/race-entries.xml";

    private final RaceEntryRepository raceEntryRepository;
    private final FileUtil fileUtil;
    private final RacerRepository racerRepository;
    private final CarRepository carRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, FileUtil fileUtil, RacerRepository racerRepository, CarRepository carRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.raceEntryRepository = raceEntryRepository;
        this.fileUtil = fileUtil;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean raceEntriesAreImported() {

        return this.raceEntryRepository.count() > 0;
    }

    @Override
    public String readRaceEntriesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACE_ENTRIES_XML_FILE_PATH);
    }

    @Override
    public String importRaceEntries() throws JAXBException {

        StringBuilder importResult = new StringBuilder();

        RaceEntryImportRootDTO raceEntryImportRootDTO = this.xmlParser
                .parseXml(RaceEntryImportRootDTO.class, RACE_ENTRIES_XML_FILE_PATH);

        Arrays.stream(raceEntryImportRootDTO.getRaceEntryImportDTOS()).forEach(raceEntryImportDTO -> {
            Racer racerEntity = this.racerRepository
                    .findByName(raceEntryImportDTO.getRacer())
                    .orElse(null);

            Car carEntity = this.carRepository
                    .findById(raceEntryImportDTO.getCarId())
                    .orElse(null);

            if(racerEntity == null || carEntity == null) {
                importResult.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());

                return;
            }

            RaceEntry raceEntryEntity =
                    this.modelMapper.map(raceEntryImportDTO, RaceEntry.class);
            raceEntryEntity.setRacer(racerEntity);
            raceEntryEntity.setCar(carEntity);
            raceEntryEntity.setRace(null);
            //Method saveAndFlush -> getBack a Object, that's means a new Entity is created
            // After save and we have a new id -> so we have a Race Entry id
            raceEntryEntity = this.raceEntryRepository.saveAndFlush(raceEntryEntity);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    raceEntryEntity.getClass().getSimpleName(),
                    raceEntryEntity.getId()))
                    .append(System.lineSeparator());
        });

        return importResult.toString().trim();
    }
}
