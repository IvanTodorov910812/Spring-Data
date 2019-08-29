package alararestaurant.domain.dtos.orders;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItemDto {
    /*
    <item>
        <name>Quarter Pounder</name>
        <quantity>2</quantity>
      </item>
     */
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="quantity")
    private Integer quantity;

    public OrderItemDto() {
    }

    @NotNull
    @Size(min=3, max=30)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Min(1)
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
