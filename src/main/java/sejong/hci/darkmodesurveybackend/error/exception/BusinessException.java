package sejong.hci.darkmodesurveybackend.error.exception;

import lombok.Getter;
import sejong.hci.darkmodesurveybackend.error.ErrorCode;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode, String message) {
        super(errorCode.name() + ":" + errorCode.getMessage() + " " + message);
        this.errorCode = errorCode;
    }
}
