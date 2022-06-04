package sejong.hci.darkmodesurveybackend.dto.stats;

import lombok.Data;
import sejong.hci.darkmodesurveybackend.entity.UiMode;

import java.util.List;

@Data
public class FindingWordStatsDto {

    private List<StatsPerQuestionDto> statsPerQuestion;

    private long totalNum;

    private long correctNum;

    private long totalSeconds;

    @Data
    public static class StatsPerQuestionDto {

        private long findingWordId;

        private UiMode uiMode;

        private long totalNum;

        private long correctNum;

        private long totalSeconds;
    }
}
