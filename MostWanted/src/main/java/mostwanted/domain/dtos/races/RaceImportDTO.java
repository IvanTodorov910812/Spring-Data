package mostwanted.domain.dtos.races;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportDTO {

    @XmlElement(name = "laps")
    private Integer laps;

    @XmlElement(name = "district-name")
    private String district;

    @XmlElement(name = "entries")
    private EntryImportRootDTO entryImportRootDTO;

    public RaceImportDTO() {

    }

    @NotNull
    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    @NotNull
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public EntryImportRootDTO getEntryImportRootDTO() {
        return entryImportRootDTO;
    }

    public void setEntryImportRootDTO(EntryImportRootDTO entryImportRootDTO) {
        this.entryImportRootDTO = entryImportRootDTO;
    }
}
