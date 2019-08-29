package app.ccb.domain.dtos.bankaccount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bank-accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountImportRootDTO {

    @XmlElement(name = "bank-account")
    private BankAccountImportDTO[] bankAccountImportDTOS;

    public BankAccountImportRootDTO() {

    }

    public BankAccountImportDTO[] getBankAccountImportDTOS() {
        return bankAccountImportDTOS;
    }

    public void setBankAccountImportDTOS(BankAccountImportDTO[] bankAccountImportDTOS) {
        this.bankAccountImportDTOS = bankAccountImportDTOS;
    }
}
