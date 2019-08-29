package cardealer.web.controllers;

import cardealer.domain.dtos.CarExportRootDTO;
import cardealer.service.CarService;
import cardealer.util.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

@Controller
public class ExportController {

    private final CarService carService;
    private final XmlParser xmlParser;

    @Autowired
    public ExportController(CarService carService, XmlParser xmlParser) {
        this.carService = carService;
        this.xmlParser = xmlParser;
    }

    public String exportCars() throws JAXBException, FileNotFoundException {
        CarExportRootDTO carExportRootDTO = this.carService.exportCars();

        this.xmlParser
                .exportToXml(carExportRootDTO, CarExportRootDTO.class, "D:\\SoftUni\\JAVADataBaseFrameworksHibernate\\CardealerXML\\src\\main\\resources\\files\\output\\cars-and-parts.xml" );
        return null;
    }
}
