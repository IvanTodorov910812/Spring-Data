package cardealer.domain.dtos;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartImportRootDTO {

    @XmlElement(name = "part")
    private PartImportDTO[] partImportDTOs;

    public PartImportRootDTO() {

    }

    public PartImportDTO[] getPartImportDTOs() {
        return partImportDTOs;
    }

    public void setPartImportDTOs(PartImportDTO[] partImportDTOs) {
        this.partImportDTOs = partImportDTOs;
    }
}
