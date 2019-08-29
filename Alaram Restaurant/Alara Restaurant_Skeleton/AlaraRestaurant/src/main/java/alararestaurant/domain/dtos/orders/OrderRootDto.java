package alararestaurant.domain.dtos.orders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderRootDto {
    @XmlElement(name = "order")
    private OrderDto[] orderImprotDtos;

    public OrderRootDto() {
    }

    public OrderDto[] getOrderImprotDtos() {
        return this.orderImprotDtos;
    }

    public void setOrderImprotDtos(OrderDto[] orderImprotDtos) {
        this.orderImprotDtos = orderImprotDtos;
    }
}
