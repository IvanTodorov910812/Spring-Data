package mostwanted.domain.dtos.races;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryImportRootDTO {

    @XmlElement(name = "entry")
    private EntryImportDTO[] entryImportDTOS;

    public EntryImportRootDTO() {

    }

    public EntryImportDTO[] getEntryImportDTOS() {
        return entryImportDTOS;
    }

    public void setEntryImportDTOS(EntryImportDTO[] entryImportDTOS) {
        this.entryImportDTOS = entryImportDTOS;
    }
}
