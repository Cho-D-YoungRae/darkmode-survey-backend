package sejong.hci.darkmodesurveybackend.repository.dto;

import lombok.Data;

@Data
public class FindingWordCorrectAnswerDto {

    private final long findingWordId;

    private final int correctAnswer;
}
