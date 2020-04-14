package response;

public enum  ResultCode {
    // success
    Ok,
    // exceptions
    UserAlreadyExists,
    IncorrectParameter,
    ConstraintViolation,
    // general
    Unauthorized,
    UnexpectedError
}
