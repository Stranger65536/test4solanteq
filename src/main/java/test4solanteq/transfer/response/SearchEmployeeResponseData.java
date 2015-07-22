package test4solanteq.transfer.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import test4solanteq.model.EmployeeModel;
import test4solanteq.model.PositionModel;

import java.util.Date;

/**
 * @author vladislav.trofimov@emc.com
 */
@SuppressWarnings({"InstanceVariableMayNotBeInitialized", "unused"})
public class SearchEmployeeResponseData {
    private String userId;
    private String firstName;
    private String lastName;
    private String middleName;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String position;

    public SearchEmployeeResponseData() {
    }

    public SearchEmployeeResponseData(
            final EmployeeModel employeeModel,
            final PositionModel positionModel) {
        this.userId = employeeModel.getUserId();
        this.firstName = employeeModel.getFirstName();
        this.lastName = employeeModel.getLastName();
        this.middleName = employeeModel.getMiddleName();
        this.birthDate = employeeModel.getBirthDate();
        this.position = positionModel.getName();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(final String position) {
        this.position = position;
    }
}