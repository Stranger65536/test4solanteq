package test4solanteq.service;

import test4solanteq.transfer.response.SearchEmployeeResponseData;

import java.util.Date;
import java.util.List;

public interface EmployeeService {
    void addUser(
            final String lastName,
            final String firstName,
            final String middleName,
            final Date birthDate,
            final String position);

    List<SearchEmployeeResponseData> searchUser(
            final String lastName,
            final String firstName,
            final String middleName,
            final Date birthDate,
            final String position
    );
}
