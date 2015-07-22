package test4solanteq.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import test4solanteq.model.EmployeeModel;
import test4solanteq.utils.Utils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static test4solanteq.dao.DaoConstants.Users.*;

/**
 * @author vladislav.trofimov@emc.com
 */
@Repository("EmployeeDao")
@SuppressWarnings({"DuplicateStringLiteralInspection", "MagicCharacter", "unused"})
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {
    @SuppressWarnings("unused")
    @Autowired
    private DataSource dataSource;

    @SuppressWarnings({
            "OverlyComplexBooleanExpression",
            "BooleanMethodNameMustStartWithQuestion",
            "MethodWithMoreThanThreeNegations"})
    private static boolean atLeastOneParameterSpecified(
            final String lastName,
            final String firstName,
            final String middleName,
            final Date birthDate,
            final String positionId) {
        return lastName != null ||
                firstName != null ||
                middleName != null ||
                birthDate != null ||
                positionId != null;
    }

    private static String removeLastAndFromQuery(final String query) {
        return query.substring(0, query.lastIndexOf(" AND "));
    }

    private static Object[] getParameters(final Object... args) {
        final Collection<Object> result = new LinkedList<>();
        for (Object o : args) {
            if (o != null) {
                result.add(o);
            }
        }
        return result.toArray();
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public String addEmployee(
            final String lastName,
            final String firstName,
            final String middleName,
            final Date birthDate,
            final String positionId) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(
                connection -> {
                    final String query = "INSERT INTO " + USERS_TABLE + " (" +
                            LAST_NAME + ", " +
                            FIRST_NAME + ", " +
                            MIDDLE_NAME + ", " +
                            BIRTH_DATE + ", " +
                            POSITION_ID +
                            ") VALUES (?,?,?,?,?)";
                    PreparedStatement ps = connection.prepareStatement(query,
                            new String[]{USER_ID});
                    ps.setNString(1, lastName);
                    ps.setNString(2, firstName);
                    ps.setNString(3, middleName);
                    ps.setDate(4, birthDate);
                    ps.setString(5, positionId);
                    return ps;
                }, keyHolder);
        return Utils.rawToHex((byte[]) keyHolder.getKeys().get(USER_ID));
    }

    @SuppressWarnings({"StringConcatenationMissingWhitespace", "VariableNotUsedInsideIf"})
    @Override
    public List<EmployeeModel> findEmployees(
            final String lastName,
            final String firstName,
            final String middleName,
            final Date birthDate,
            final String positionId) {
        String query = "SELECT * FROM " + USERS_TABLE + ' ';
        if (atLeastOneParameterSpecified(lastName, firstName,
                middleName, birthDate, positionId)) {
            query += removeLastAndFromQuery("WHERE " +
                    (lastName == null ? "" : LAST_NAME + " = ? AND ") +
                    (firstName == null ? "" : FIRST_NAME + " = ? AND ") +
                    (middleName == null ? "" : MIDDLE_NAME + " = ? AND ") +
                    (birthDate == null ? "" : BIRTH_DATE + " = ? AND ") +
                    (positionId == null ? "" : POSITION_ID + " = ? AND "));
        }

        return getJdbcTemplate().query(query, getParameters(
                lastName,
                firstName,
                middleName,
                birthDate,
                positionId
        ), new BeanPropertyRowMapper<>(EmployeeModel.class));
    }
}