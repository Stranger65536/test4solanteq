package test4solanteq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import test4solanteq.dao.EmployeeDao;
import test4solanteq.model.EmployeeModel;
import test4solanteq.transfer.response.SearchEmployeeResponseData;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("ALL")
@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    PositionService positionService;

    @Override
    public void addUser(
            final String lastName,
            final String firstName,
            final String middleName,
            final Date birthDate,
            final String position) {
        employeeDao.addEmployee(lastName, firstName, middleName,
                birthDate == null ? null : new java.sql.Date(birthDate.getTime()), position);
    }

    @Override
    public List<SearchEmployeeResponseData> searchUser(
            final String lastName,
            final String firstName,
            final String middleName,
            final Date birthDate,
            final String position) {
        final List<EmployeeModel> employees = employeeDao.findEmployees(
                lastName, firstName, middleName,
                birthDate == null ? null : new java.sql.Date(birthDate.getTime()), position);
        return joinEmployeesWithPositions(employees);
    }

    List<SearchEmployeeResponseData> joinEmployeesWithPositions(
            final Collection<EmployeeModel> employees) {
        final LinkedList<SearchEmployeeResponseData> result = new LinkedList<>();
        employees.stream().forEach(employee -> result.add(new SearchEmployeeResponseData(employee,
                positionService.getPosition(employee.getPositionId()))));
        return result;
    }
}
