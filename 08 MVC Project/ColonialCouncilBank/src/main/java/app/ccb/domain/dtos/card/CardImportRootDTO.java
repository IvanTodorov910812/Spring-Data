package app.ccb.domain.dtos.card;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardImportRootDTO {

    @XmlElement(name = "card")
    private CardImportDTO[] cardImportDTOs;

    public CardImportRootDTO() {

    }

    public CardImportDTO[] getCardImportDTOs() {
        return cardImportDTOs;
    }

    public void setCardImportDTOs(CardImportDTO[] cardImportDTOs) {
        this.cardImportDTOs = cardImportDTOs;
    }
}
