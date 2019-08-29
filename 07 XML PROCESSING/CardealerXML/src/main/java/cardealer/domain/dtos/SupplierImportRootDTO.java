package cardealer.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportRootDTO {

    @XmlElement(name = "supplier")
    private SupplierImportDTO[] supplierImportDTOS;

    public SupplierImportRootDTO() {

    }

    public SupplierImportDTO[] getSupplierImportDTOS() {
        return supplierImportDTOS;
    }

    public void setSupplierImportDTOS(SupplierImportDTO[] supplierImportDTOS) {
        this.supplierImportDTOS = supplierImportDTOS;
    }
}
