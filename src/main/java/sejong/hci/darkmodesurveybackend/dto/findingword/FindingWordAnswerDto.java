package sejong.hci.darkmodesurveybackend.dto.findingword;

import lombok.Data;
import sejong.hci.darkmodesurveybackend.entity.UiMode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class FindingWordAnswerDto {

    @NotNull
    private Long findingWordId;

    @NotNull
    @Positive
    private Integer answer;

    @NotNull
    private UiMode uiMode;

    @NotNull
    @Positive
    private Integer estimatedSeconds;
}
