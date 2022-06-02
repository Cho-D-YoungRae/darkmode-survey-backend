package sejong.hci.darkmodesurveybackend.dto.findingword;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class FindingWordAnswersDto {

    @NotNull
    private List<FindingWordAnswerDto> answers;
}
