package cardealer.service;

import cardealer.domain.dtos.SupplierImportDTO;
import cardealer.domain.dtos.SupplierImportRootDTO;
import cardealer.domain.entities.Supplier;
import cardealer.repository.SupplierRepository;
import cardealer.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importSuppliers(SupplierImportRootDTO supplierImportRootDTO) {

            for (SupplierImportDTO supplierImportDTO: supplierImportRootDTO.getSupplierImportDTOS()) {
                if(!this.validationUtil.isValid(supplierImportDTO)) {
                    System.out.println("Something is went wrong");

                    continue;
                }

                Supplier supplierEntity = this.modelMapper.map(supplierImportDTO, Supplier.class);

                this.supplierRepository.saveAndFlush(supplierEntity);
            }
    }
}
