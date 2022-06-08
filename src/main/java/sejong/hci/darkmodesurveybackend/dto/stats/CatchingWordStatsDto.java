package sejong.hci.darkmodesurveybackend.dto.stats;

import lombok.Data;
import sejong.hci.darkmodesurveybackend.entity.UiMode;

import java.util.List;

@Data
public class CatchingWordStatsDto {

    private List<StatsPerQuestionDto> statsPerQuestion;

    private long totalNum;

    private long correctNum;

    @Data
    public static class StatsPerQuestionDto {

        private long catchingWordId;

        private UiMode uiMode;

        private long totalNum;

        private long correctNum;
    }
}
