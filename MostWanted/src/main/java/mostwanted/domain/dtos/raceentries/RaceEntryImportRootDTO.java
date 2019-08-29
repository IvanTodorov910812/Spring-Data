package mostwanted.domain.dtos.raceentries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryImportRootDTO {

    @XmlElement(name = "race-entry")
    private RaceImportDTO[] raceEntryImportDTOS;

    public RaceEntryImportRootDTO() {

    }

    public RaceImportDTO[] getRaceEntryImportDTOS() {
        return raceEntryImportDTOS;
    }

    public void setRaceEntryImportDTOS(RaceImportDTO[] raceEntryImportDTOS) {
        this.raceEntryImportDTOS = raceEntryImportDTOS;
    }
}
