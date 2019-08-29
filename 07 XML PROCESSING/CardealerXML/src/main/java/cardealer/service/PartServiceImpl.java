package cardealer.service;

import cardealer.domain.dtos.PartImportDTO;
import cardealer.domain.dtos.PartImportRootDTO;
import cardealer.domain.entities.Part;
import cardealer.domain.entities.Supplier;
import cardealer.repository.PartRepository;
import cardealer.repository.SupplierRepository;
import cardealer.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;


    @Autowired
    public PartServiceImpl(PartRepository partRepository, ValidationUtil validationUtil, ModelMapper modelMapper, SupplierRepository supplierRepository) {
        this.partRepository = partRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void importParts(PartImportRootDTO partImportRootDTO) {

        for (PartImportDTO partImportDTO : partImportRootDTO.getPartImportDTOs()) {

            if (!this.validationUtil.isValid(partImportDTO)) {
                System.out.println("Something is went wrong");

                continue;
            }

            Part partEntity = this.modelMapper.map(partImportDTO, Part.class);
            partEntity.setSupplier(this.getRandomSupplier());
            this.partRepository.saveAndFlush(partEntity);
        }
    }

    private Supplier getRandomSupplier() {
        Random random = new Random();
        int randomIndex = random.nextInt((int) (this.supplierRepository.count() - 1 )) + 1;
        return this.supplierRepository.findAll().get(randomIndex);

    }

}


