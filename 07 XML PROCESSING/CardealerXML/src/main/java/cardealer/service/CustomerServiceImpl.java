package cardealer.service;

import cardealer.domain.dtos.CustomerImportDTO;
import cardealer.domain.dtos.CustomerImportRootDTO;
import cardealer.domain.entities.Customer;
import cardealer.repository.CustomerRepository;
import cardealer.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importCustomers(CustomerImportRootDTO customerImportRootDTO) {
            for (CustomerImportDTO customerImportDTO: customerImportRootDTO.getCustomerImportDTOS()) {
                if(!this.validationUtil.isValid(customerImportDTO)) {
                    System.out.println("Something went wrong.");

                    continue;
                }

                Customer customer = this.modelMapper.map(customerImportDTO, Customer.class);
                customer.setBirthDate(LocalDate.parse(customerImportDTO.getBirthDate()));
                this.customerRepository.saveAndFlush(customer);
            }
    }
}
