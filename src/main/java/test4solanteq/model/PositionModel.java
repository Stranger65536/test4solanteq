package test4solanteq.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author vladislav.trofimov@emc.com
 */
@SuppressWarnings({"InstanceVariableMayNotBeInitialized", "unused"})
public class PositionModel {
    @NotNull
    @Size(min = 32, max = 32)
    private String positionId;
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(final String positionId) {
        this.positionId = positionId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}