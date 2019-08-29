package app.ccb.services;

import app.ccb.domain.dtos.ClientImportDto;
import app.ccb.domain.entities.Card;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.ClientRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ClientServiceImpl implements ClientService {

    private final static String CLIENTS_JSON_FILE_PATH =
             System.getProperty("user.dir") + "/src/main/resources/files/clients.json";
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean clientsAreImported() {
        return this.clientRepository.count() != 0;
    }

    @Override
    public String readClientsJsonFile() throws IOException {
        return this.fileUtil.readFile(CLIENTS_JSON_FILE_PATH);
    }

    @Override
    public String importClients(String clients) {
        StringBuilder importResult = new StringBuilder();
        ClientImportDto[] clientImportDtos = this.gson.fromJson(clients, ClientImportDto[].class);

        for (ClientImportDto clientImportDto : clientImportDtos) {
            if (!this.validationUtil.isValid(clientImportDto)) {
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());

                continue;
            }

            Employee employeeEntity = this.employeeRepository
                    .findByFullName(clientImportDto.getAppointedEmployee())
                    .orElse(null);

            if (employeeEntity == null) {
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());

                continue;
            }

            Client clientEntity = this.clientRepository.findByFullName(String.format("%s %s",
                    clientImportDto.getFirstName(), clientImportDto.getLastName())).orElse(null);

            if(clientEntity != null){
                importResult.append("Error: Incorrect Data!")
                        .append(System.lineSeparator());

                continue;
            }


            clientEntity = this.modelMapper.map(clientImportDto, Client.class);
            clientEntity
                    .setFullName(String.format("%s %s", clientImportDto.getFirstName(), clientImportDto.getLastName()));
            clientEntity.getEmployees().add(employeeEntity);

            this.clientRepository.saveAndFlush(clientEntity);

            importResult.append(String.format("Successfully imported Client - %s", clientEntity.getFullName())).append(System.lineSeparator());

        }

        return importResult.toString().trim();
    }

    @Override
    public String exportFamilyGuy() {
       Client clientEntity = this.clientRepository
               .exportFamilyGuy()
               .stream()
               .findFirst()
               .orElse(null);

       StringBuilder exportResult = new StringBuilder();
        //%s - string
       exportResult.append(String.format("Full name: %s",
               clientEntity.getFullName()))
               .append(System.lineSeparator());
        //%d - digit
        exportResult.append(String.format("Age: %d",
                clientEntity.getAge()))
                .append(System.lineSeparator());

        exportResult.append(String.format("Bank Account: %s",
                clientEntity.getBankAccount().getAccountNumber()))
                .append(System.lineSeparator());

        for (Card cardEntity : clientEntity.getBankAccount().getCards()) {

            exportResult.append(String.format(" Card Number: %s",
                    cardEntity.getCardNumber()))
                    .append(System.lineSeparator());

            exportResult.append(String.format(" Card status: %s",
                    cardEntity.getCardStatus()))
                    .append(System.lineSeparator());
            exportResult.append(System.lineSeparator());
        }
        return exportResult.toString().trim();
    }
}
