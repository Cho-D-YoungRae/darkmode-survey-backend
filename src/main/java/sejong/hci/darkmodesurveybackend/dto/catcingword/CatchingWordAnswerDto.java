package sejong.hci.darkmodesurveybackend.dto.catcingword;

import lombok.Data;
import sejong.hci.darkmodesurveybackend.entity.UiMode;

import javax.validation.constraints.NotNull;

@Data
public class CatchingWordAnswerDto {

    @NotNull
    private Long catchingWordId;

    @NotNull
    private String answer;

    @NotNull
    private UiMode uiMode;
}
