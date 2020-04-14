package response;

import java.util.Objects;

public class StandardResponse {

    private ResultCode resultCode;
    private String errorMessage;

    public ResultCode getResultCode() {
        return resultCode;
    }

    public StandardResponse() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StandardResponse)) return false;
        StandardResponse that = (StandardResponse) o;
        return resultCode == that.resultCode &&
                Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCode, errorMessage);
    }
}
