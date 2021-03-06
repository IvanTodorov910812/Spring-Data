package app.ccb.domain.dtos.card;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardImportDTO {

    @XmlElement(name = "card-number")
    private String cardNumber;

    @XmlAttribute(name = "status")
    private String cardStatus;

    @XmlAttribute(name = "account-number")
    private String accountNumber;

    public CardImportDTO() {

    }

    @NotNull
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @NotNull
    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
