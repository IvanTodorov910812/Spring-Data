package cardealer.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(namespace = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartExportRootDTO {

    @XmlElement(name = "part")
    private List<PartExportDTO> partExportDTOS;

    public PartExportRootDTO() {

    }

    public List<PartExportDTO> getPartExportDTOS() {
        return partExportDTOS;
    }

    public void setPartExportDTOS(List<PartExportDTO> partExportDTOS) {
        this.partExportDTOS = partExportDTOS;
    }


}
