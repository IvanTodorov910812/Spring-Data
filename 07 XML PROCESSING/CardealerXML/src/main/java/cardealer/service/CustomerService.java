package cardealer.service;

import cardealer.domain.dtos.CustomerImportRootDTO;

public interface CustomerService {

    void importCustomers(CustomerImportRootDTO customerImportRootDTO);
}
