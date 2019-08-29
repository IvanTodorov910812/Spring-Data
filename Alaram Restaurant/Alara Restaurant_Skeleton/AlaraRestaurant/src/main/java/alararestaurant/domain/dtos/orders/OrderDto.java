package alararestaurant.domain.dtos.orders;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDto {
   /*<order>
    <customer>Garry</customer>
    <employee>Maxwell Shanahan</employee>
    <date-time>21/08/2017 13:22</date-time>
    <type>ForHere</type>
    <items>
      <item>
    <name>Quarter Pounder</name>
        <quantity>2</quantity>
      </item>
      */
   @XmlElement(name="customer")
    private String customer;
   @XmlElement(name="employee")
    private String employee;
   @XmlElement(name="date-time")
    private String date;
   @XmlElement(name="type")
    private String type;
   @XmlElement(name="items")
    private OrderItemRDto orderItemsImportRootDto;

    public OrderDto() {

    }

    @NotNull
    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @NotNull
    @Size(min=3,max=30)
    public String getEmployee() {
        return this.employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    @NotNull
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public OrderItemRDto getOrderItemsImportRootDto() {
        return this.orderItemsImportRootDto;
    }

    public void setOrderItemsImportRootDto(OrderItemRDto orderItemsImportRootDto) {
        this.orderItemsImportRootDto = orderItemsImportRootDto;
    }
}
