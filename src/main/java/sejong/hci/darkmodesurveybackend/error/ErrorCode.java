package sejong.hci.darkmodesurveybackend.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static javax.servlet.http.HttpServletResponse.*;
import static sejong.hci.darkmodesurveybackend.error.ErrorCode.Domain.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_VALUE(COMMON, SC_BAD_REQUEST, " Invalid Input Value."),
    INVALID_TYPE_VALUE(COMMON, SC_BAD_REQUEST,  " Invalid Type Value."),
    RESOURCE_NOT_FOUND(COMMON, SC_BAD_REQUEST, " Resource is Not Found."),
    NOT_OWNED_RESOURCE(COMMON, SC_FORBIDDEN, "Resource is not owned."),
    ;

    private final Domain domain;

    private final int status;

    private final String message;

    enum Domain {
        COMMON,
    }
}
