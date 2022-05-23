package sejong.hci.darkmodesurveybackend.dto.findingword;

import lombok.Data;

@Data
public class FindingWordQuestionsDto {

    private Long id;

    private String text;

    private String wordToFind;
}
