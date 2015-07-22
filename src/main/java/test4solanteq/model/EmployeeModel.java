package test4solanteq.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author vladislav.trofimov@emc.com
 */
@SuppressWarnings({"InstanceVariableMayNotBeInitialized", "unused"})
public class EmployeeModel {
    @NotNull
    @Size(min = 32, max = 32)
    private String userId;
    @NotNull
    @Size(min = 1, max = 255)
    private String firstName;
    @NotNull
    @Size(min = 1, max = 255)
    private String lastName;
    @Size(min = 0, max = 255)
    private String middleName;
    @NotNull
    @Past
    private Date birthDate;
    @NotNull
    private String positionId;

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

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(final String positionId) {
        this.positionId = positionId;
    }
}