package cardealer.service;

import cardealer.domain.dtos.PartImportRootDTO;

public interface PartService {

    void importParts(PartImportRootDTO partImportRootDTO);
}
