package alararestaurant.service;

import alararestaurant.domain.dtos.orders.OrderRootDto;
import alararestaurant.domain.dtos.orders.OrderDto;
import alararestaurant.domain.dtos.orders.OrderItemDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Item;
import alararestaurant.domain.entities.Order;
import alararestaurant.domain.entities.OrderItem;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import alararestaurant.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final String FILE_PATH_ORDERS=System.getProperty("user.dir") + "/src/main/resources/files/orders.xml";
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public OrderServiceImpl(OrderRepository orderRepository, EmployeeRepository employeeRepository, ItemRepository itemRepository, OrderItemRepository orderItemRepository, FileUtil fileUtil, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean ordersAreImported() {
      return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() throws IOException {
        return this.fileUtil.readFile(FILE_PATH_ORDERS);
    }

    @Override
    public String importOrders() throws JAXBException {
       StringBuilder sb= new StringBuilder();
       boolean isHaveItem=true;
        OrderRootDto orderItemsImportRootDto=this.xmlParser.parseXml(OrderRootDto.class,FILE_PATH_ORDERS);
        for (OrderDto orderImprotDto : orderItemsImportRootDto.getOrderImprotDtos()) {
            if(!this.validationUtil.isValid(orderImprotDto)){
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }
            Order order=this.modelMapper.map(orderImprotDto,Order.class);
            order.setDate(LocalDate.parse(orderImprotDto.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            Employee employee=this.employeeRepository.findByName(orderImprotDto.getEmployee()).orElse(null);
            if(employee==null){
                continue;
            }
            order.setEmployee(employee);
            for (OrderItemDto itemImportDtoOrder : orderImprotDto.getOrderItemsImportRootDto().getItemImportDtoOrders()) {
                Item item=this.itemRepository.findByName(itemImportDtoOrder.getName()).orElse(null);
                if(item==null){
                    isHaveItem=false;

                }
            }
            List<OrderItem> orderItemList=new ArrayList<>();
            if(isHaveItem){
                this.orderRepository.saveAndFlush(order);
                for (OrderItemDto itemImportDtoOrder : orderImprotDto.getOrderItemsImportRootDto().getItemImportDtoOrders()) {
                    Item item=this.itemRepository.findByName(itemImportDtoOrder.getName()).orElse(null);
                  OrderItem orderItem=new OrderItem();
                  orderItem.setQuantity(itemImportDtoOrder.getQuantity());
                  orderItem.setItem(item);
                  orderItem.setOrder(order);
                  this.orderItemRepository.saveAndFlush(orderItem);

                }
            }



            System.out.println();
            sb.append(String.format("Order for %s on %s added",orderImprotDto.getCustomer(),orderImprotDto.getDate())).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        StringBuilder sb=new StringBuilder();
       List<Order> orders=this.orderRepository.findByFinishedFillip();
        for (Order order : orders) {
            sb.append(String.format("Name: %s",order.getEmployee().getName())).append(System.lineSeparator());
            sb.append("Orders: ").append(System.lineSeparator());
            sb.append("\tCustomer: ").append(order.getCustomer()).append(System.lineSeparator());
            sb.append("\tItems: ").append(System.lineSeparator());
            for (OrderItem orderItem : order.getOrderItems()) {

                sb.append("\t\tName: ").append(orderItem.getItem().getName()).append(System.lineSeparator());
                sb.append("\t\tPrice: ").append(orderItem.getItem().getPrice()).append(System.lineSeparator());
                sb.append("\t\tQuantity: ").append(orderItem.getQuantity()).append(System.lineSeparator());
                sb.append(System.lineSeparator());
                
            }
        }
        return sb.toString().trim();
    }
}
