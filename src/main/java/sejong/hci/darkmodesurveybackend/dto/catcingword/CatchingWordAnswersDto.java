package sejong.hci.darkmodesurveybackend.dto.catcingword;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CatchingWordAnswersDto {

    @NotNull
    private List<CatchingWordAnswerDto> answers;
}
