package app.ccb.services;

import app.ccb.domain.dtos.bankaccount.BankAccountImportDTO;
import app.ccb.domain.dtos.bankaccount.BankAccountImportRootDTO;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Client;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final static String BANK_ACCOUNTS_XML_FILE_PATH =
            System.getProperty("user.dir") + "/src/main/resources/files/bank-accounts.xml";
    private final BankAccountRepository bankAccountRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, FileUtil fileUtil, ValidationUtil validationUtil, ClientRepository clientRepository, ModelMapper modelMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean bankAccountsAreImported() {
        return this.bankAccountRepository.count() != 0;
    }

    @Override
    public String readBankAccountsXmlFile() throws IOException {
        return this.fileUtil.readFile(BANK_ACCOUNTS_XML_FILE_PATH);
    }

    @Override
    public String importBankAccounts() throws JAXBException {
        StringBuilder importResult = new StringBuilder();

        JAXBContext context = JAXBContext.newInstance(BankAccountImportRootDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        BankAccountImportRootDTO bankAccountImportRootDTO =
                (BankAccountImportRootDTO) unmarshaller.unmarshal(new File(BANK_ACCOUNTS_XML_FILE_PATH));

        for (BankAccountImportDTO bankAccountImportDTO : bankAccountImportRootDTO.getBankAccountImportDTOS()) {
            if(!this.validationUtil.isValid(bankAccountImportDTO)) {
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());

                continue;
            }

            Client clientEntity = this.clientRepository
                    .findByFullName(bankAccountImportDTO.getClient())
                    .orElse(null);

            if(clientEntity == null){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());

                continue;
            }

            BankAccount bankAccountEntity =
                    this.modelMapper.map(bankAccountImportDTO, BankAccount.class);
            bankAccountEntity.setClient(clientEntity);

            this.bankAccountRepository.saveAndFlush(bankAccountEntity);

            importResult.append(String.format("Successfully imported Bank Account - %s",
                    bankAccountEntity.getAccountNumber())).append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }
}
