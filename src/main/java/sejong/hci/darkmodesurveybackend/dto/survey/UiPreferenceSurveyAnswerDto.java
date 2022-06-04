package sejong.hci.darkmodesurveybackend.dto.survey;

import lombok.Data;
import sejong.hci.darkmodesurveybackend.entity.UiMode;

import javax.validation.constraints.NotNull;

@Data
public class UiPreferenceSurveyAnswerDto {

    @NotNull
    private UiMode preferredUiMode;
}
