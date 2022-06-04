package sejong.hci.darkmodesurveybackend.dto.findingword;

import lombok.Data;

@Data
public class FindingWordCorrectAnswerDto {

    private final long findingWordId;

    private final int correctAnswer;
}
