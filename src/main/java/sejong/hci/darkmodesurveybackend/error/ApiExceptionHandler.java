package sejong.hci.darkmodesurveybackend.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sejong.hci.darkmodesurveybackend.dto.common.ApiResult;
import sejong.hci.darkmodesurveybackend.dto.error.ErrorResponseDto;
import sejong.hci.darkmodesurveybackend.error.exception.BusinessException;

import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiResult<Void>> handleBusinessException(BusinessException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponseDto response = ErrorResponseDto.of(errorCode);
        ApiResult<Void> result = ApiResult.<Void>builder()
                .success(false)
                .error(response)
                .build();
        return new ResponseEntity<>(result, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ApiResult<Void>> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        ErrorResponseDto response = ErrorResponseDto.of(ex);
        ApiResult<Void> result = ApiResult.<Void>builder()
                .success(false)
                .error(response)
                .build();
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ApiResult<Void>> handleAccessDeniedException(AccessDeniedException ex) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        String message = "Access is Denied";
        ErrorResponseDto response = new ErrorResponseDto(status.value(), message);
        ApiResult<Void> result = ApiResult.<Void>builder()
                .success(false)
                .error(response)
                .build();
        return new ResponseEntity<>(result, status);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiResult<Void>> handleException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Server Error";
        ErrorResponseDto response = new ErrorResponseDto(status.value(), message);
        ApiResult<Void> result = ApiResult.<Void>builder()
                .success(false)
                .error(response)
                .build();
        return new ResponseEntity<>(result, status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(
            BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ErrorResponseDto response = ErrorResponseDto.of(errorCode, ex.getBindingResult());
        ApiResult<Void> result = ApiResult.<Void>builder()
                .success(false)
                .error(response)
                .build();
        return handleExceptionInternal(ex, result, headers, HttpStatus.valueOf(errorCode.getStatus()), request);
    }
}
