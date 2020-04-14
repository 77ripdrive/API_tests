package response;

import java.util.List;

public class GetClientResponse {

    private ResultCode resultCode;
    private List<String> clients;

    public ResultCode getResultCode() {
        return resultCode;
    }

    public List<String> getClients() {
        return clients;
    }
}
