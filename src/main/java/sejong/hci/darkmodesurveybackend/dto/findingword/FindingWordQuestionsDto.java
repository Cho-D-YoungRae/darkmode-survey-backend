package sejong.hci.darkmodesurveybackend.dto.findingword;

import lombok.Data;

@Data
public class FindingWordQuestionsDto {

    private Long findingWordId;

    private String text;

    private String word;
}
