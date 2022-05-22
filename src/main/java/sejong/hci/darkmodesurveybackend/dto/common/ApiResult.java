package sejong.hci.darkmodesurveybackend.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import sejong.hci.darkmodesurveybackend.dto.error.ErrorResponseDto;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {

    @Builder.Default
    private boolean success = true;

    private T data;

    private ErrorResponseDto error;
}
