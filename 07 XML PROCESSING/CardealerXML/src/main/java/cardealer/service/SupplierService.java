package cardealer.service;

import cardealer.domain.dtos.SupplierImportRootDTO;
import cardealer.domain.entities.Supplier;

public interface SupplierService {

    void importSuppliers(SupplierImportRootDTO supplierImportRootDTO);
}
