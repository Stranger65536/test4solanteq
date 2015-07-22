package test4solanteq.transfer.request;

import java.util.Date;

/**
 * @author vladislav.trofimov@emc.com
 */
@SuppressWarnings({"InstanceVariableMayNotBeInitialized", "unused"})
public class SearchEmployeeRequestBody {
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private String positionId;

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