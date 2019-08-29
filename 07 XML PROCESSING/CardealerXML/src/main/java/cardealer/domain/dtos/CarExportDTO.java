package cardealer.domain.dtos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportDTO {

    @XmlAttribute(name = "make")
    private String make;

    @XmlAttribute(name = "model")
    private String model;

    @XmlAttribute(name = "travelled-distance")
    private Double travelledDistance;

    @XmlElement(name = "parts")
    private PartExportRootDTO partExportRootDTO;

    public CarExportDTO() {

    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Double travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public PartExportRootDTO getPartExportRootDTO() {
        return partExportRootDTO;
    }

    public void setPartExportRootDTO(PartExportRootDTO partExportRootDTO) {
        this.partExportRootDTO = partExportRootDTO;
    }
}
