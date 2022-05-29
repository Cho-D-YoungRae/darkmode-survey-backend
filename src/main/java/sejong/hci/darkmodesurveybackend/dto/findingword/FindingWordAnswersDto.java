package sejong.hci.darkmodesurveybackend.dto.findingword;

import lombok.Data;

import java.util.List;

@Data
public class FindingWordAnswersDto {

    private List<FindingWordAnswerDto> answers;
}
