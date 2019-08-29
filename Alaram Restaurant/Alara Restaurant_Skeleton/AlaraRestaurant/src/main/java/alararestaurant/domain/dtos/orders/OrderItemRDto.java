package alararestaurant.domain.dtos.orders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItemRDto {
    @XmlElement(name="item")
    private OrderItemDto[] itemImportDtoOrders;

    public OrderItemRDto() {
    }

    public OrderItemDto[] getItemImportDtoOrders() {
        return this.itemImportDtoOrders;
    }

    public void setItemImportDtoOrders(OrderItemDto[] itemImportDtoOrders) {
        this.itemImportDtoOrders = itemImportDtoOrders;
    }
}
