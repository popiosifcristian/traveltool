package travel.tool.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.tool.epo.EmployeeEpo;
import travel.tool.model.Employee;

/**
 * @author ipop
 */
@Service
public class EmployeeMapper extends GenericMapper<Employee, EmployeeEpo> {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Employee toInternal(EmployeeEpo epo) {
        return new Employee(epo.getId(), epo.getUsername(), epo.getPassword(), epo.getEmail(), epo.getFirstName(), epo.getLastName(), epo.getPhoneNumber(), companyMapper.toInternal(epo.getAgency()));
    }

    @Override
    public EmployeeEpo toExternal(Employee model) {
        return new EmployeeEpo(model.getId(), model.getUsername(), model.getPassword(), model.getEmail(), model.getFirstName(), model.getLastName(), model.getPhoneNumber(), companyMapper.toExternal(model.getAgency()));
    }
}
