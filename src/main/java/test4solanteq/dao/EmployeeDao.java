package test4solanteq.dao;

import test4solanteq.model.EmployeeModel;

import java.sql.Date;
import java.util.List;

/**
 * @author vladislav.trofimov@emc.com
 */
public interface EmployeeDao {
    @SuppressWarnings("UnusedReturnValue")
    String addEmployee(
            final String lastName,
            final String firstName,
            final String middleName,
            final Date birthDate,
            final String positionId);

    List<EmployeeModel> findEmployees(
            final String lastName,
            final String firstName,
            final String middleName,
            final Date birthDate,
            final String positionId);
}