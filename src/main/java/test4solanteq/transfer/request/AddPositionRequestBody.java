package test4solanteq.transfer.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings({"InstanceVariableMayNotBeInitialized", "unused"})
public class AddPositionRequestBody {
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
