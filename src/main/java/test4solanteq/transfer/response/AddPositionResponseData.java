package test4solanteq.transfer.response;

@SuppressWarnings("unused")
public class AddPositionResponseData {
    private String id;

    public AddPositionResponseData(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }
}
