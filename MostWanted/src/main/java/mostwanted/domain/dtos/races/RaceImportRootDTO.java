package mostwanted.domain.dtos.races;

import mostwanted.domain.dtos.raceentries.RaceImportDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportRootDTO {

    @XmlElement(name = "race")
    private RaceImportDTO[] raceImportDTOS;

    public RaceImportRootDTO() {

    }

    public RaceImportDTO[] getRaceImportDTOS() {
        return raceImportDTOS;
    }

    public void setRaceImportDTOS(RaceImportDTO[] raceImportDTOS) {
        this.raceImportDTOS = raceImportDTOS;
    }
}
