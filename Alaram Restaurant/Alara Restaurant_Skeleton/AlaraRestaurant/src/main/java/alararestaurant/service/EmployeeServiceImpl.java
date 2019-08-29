package alararestaurant.service;

import alararestaurant.domain.dtos.EmployeeImportDto;
import alararestaurant.domain.dtos.PositionImportDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final String FILE_PATH_EMPLOYEES=System.getProperty("user.dir") + "/src/main/resources/files/employees.json";
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeesAreImported() {
       return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {

        return this.fileUtil.readFile(FILE_PATH_EMPLOYEES);
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder sb= new StringBuilder();
        EmployeeImportDto[] employeeImportDtos=this.gson.fromJson(employees,EmployeeImportDto[].class);
        for (EmployeeImportDto employeeImportDto : employeeImportDtos) {
            PositionImportDto positionImportDto=new PositionImportDto();
            positionImportDto.setName(employeeImportDto.getPosition());
            Position position=this.positionRepository.findByName(employeeImportDto.getPosition()).orElse(null);
            if(!this.validationUtil.isValid(employeeImportDto) || !this.validationUtil.isValid(positionImportDto)){
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }
            Employee employee=this.modelMapper.map(employeeImportDto,Employee.class);
            Position position1=this.modelMapper.map(positionImportDto,Position.class);
            if(position==null){
                this.positionRepository.saveAndFlush(position1);
            }
            position=this.positionRepository.findByName(employeeImportDto.getPosition()).orElse(null);
            employee.setPosition(position);
            this.employeeRepository.saveAndFlush(employee);
            sb.append(String.format("Record %s successfully imported.",employee.getName())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
