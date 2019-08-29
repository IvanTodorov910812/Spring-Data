package cardealer.service;


import cardealer.domain.dtos.CarExportRootDTO;
import cardealer.domain.dtos.CarImportRootDTO;

public interface CarService {

    void importCars(CarImportRootDTO carImportRootDTO);

    CarExportRootDTO exportCars();

}
