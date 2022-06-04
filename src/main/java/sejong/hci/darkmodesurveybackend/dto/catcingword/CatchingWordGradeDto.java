package sejong.hci.darkmodesurveybackend.dto.catcingword;

import lombok.Data;
import sejong.hci.darkmodesurveybackend.entity.CatchingWord;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class CatchingWordGradeDto {

    private final List<MarkingDto> markings;

    private final int perfectScore;

    private final int score;

    public CatchingWordGradeDto(List<CatchingWordAnswerDto> submittedAnswers, List<CatchingWordDto> correctAnswer) {
        Map<Long, CatchingWordDto> idToCorrectAnswer = correctAnswer.stream()
                .collect(Collectors.toMap(CatchingWordDto::getId, Function.identity()));
        this.markings = submittedAnswers.stream()
                .map(submittedAnswer -> new MarkingDto(
                        submittedAnswer, idToCorrectAnswer.get(submittedAnswer.getCatchingWordId())
                )).collect(Collectors.toList());
        this.perfectScore = this.markings.size();
        this.score = (int) markings.stream().filter(MarkingDto::isCorrect).count();
    }

    @Data
    private static class MarkingDto {

        private final long catchingWordId;

        private final String submittedAnswer;

        private final String correctAnswer;

        private final boolean correct;

        public MarkingDto(CatchingWordAnswerDto submitted, CatchingWordDto correct) {
            this.catchingWordId = submitted.getCatchingWordId();
            this.submittedAnswer = submitted.getAnswer();
            this.correctAnswer = correct.getWord();
            this.correct = Objects.equals(this.submittedAnswer, this.correctAnswer);
        }
    }
}
