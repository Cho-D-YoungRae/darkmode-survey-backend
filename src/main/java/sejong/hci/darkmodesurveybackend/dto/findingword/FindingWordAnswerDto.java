package sejong.hci.darkmodesurveybackend.dto.findingword;

import lombok.Data;

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
    @Positive
    private Integer estimatedSeconds;
}
