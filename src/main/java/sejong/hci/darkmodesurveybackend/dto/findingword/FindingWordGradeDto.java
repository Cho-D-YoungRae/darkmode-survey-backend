package sejong.hci.darkmodesurveybackend.dto.findingword;

import lombok.Data;
import sejong.hci.darkmodesurveybackend.repository.dto.FindingWordCorrectAnswerDto;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class FindingWordGradeDto {

    private final List<MarkingDto> markings;

    private final int perfectScore;

    private final int score;

    public FindingWordGradeDto(List<FindingWordAnswerDto> submittedAnswers, List<FindingWordCorrectAnswerDto> correctAnswers) {
        Map<Long, FindingWordCorrectAnswerDto> idToCorrectAnswer = correctAnswers.stream().collect(Collectors.toMap(
                FindingWordCorrectAnswerDto::getFindingWordId, Function.identity()));
        this.markings = submittedAnswers.stream()
                .map(submittedAnswer -> new MarkingDto(
                        submittedAnswer, idToCorrectAnswer.get(submittedAnswer.getFindingWordId())))
                .collect(Collectors.toList());
        this.perfectScore = this.markings.size();
        this.score = (int) markings.stream().filter(MarkingDto::isCorrect).count();
    }

    @Data
    private static class MarkingDto {

        private final long findingWordId;

        private final int submittedAnswer;

        private final int correctAnswer;

        private final boolean isCorrect;

        public MarkingDto(FindingWordAnswerDto submitted, FindingWordCorrectAnswerDto correct) {
            this.findingWordId = submitted.getFindingWordId();
            this.submittedAnswer = submitted.getAnswer();
            this.correctAnswer = correct.getCorrectAnswer();
            this.isCorrect = (this.submittedAnswer == this.correctAnswer);
        }
    }
}
