package cardealer.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportRootDTO {

    @XmlElement(name = "car")
    private CarImportDTO[] carImportDTOS;

    public CarImportRootDTO() {

    }

    public CarImportDTO[] getCarImportDTOS() {
        return carImportDTOS;
    }

    public void setCarImportDTOS(CarImportDTO[] carImportDTOS) {
        this.carImportDTOS = carImportDTOS;
    }
}
