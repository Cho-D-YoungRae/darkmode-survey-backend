package sejong.hci.darkmodesurveybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.hci.darkmodesurveybackend.dto.findingword.FindingWordCorrectAnswerDto;
import sejong.hci.darkmodesurveybackend.dto.stats.FindingWordStatsDto;
import sejong.hci.darkmodesurveybackend.entity.FindingWordAnswer;
import sejong.hci.darkmodesurveybackend.entity.UiMode;
import sejong.hci.darkmodesurveybackend.repository.CatchingWordAnswerRepository;
import sejong.hci.darkmodesurveybackend.repository.FindingWordAnswerRepository;
import sejong.hci.darkmodesurveybackend.repository.FindingWordRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StatsService {

    private final FindingWordRepository findingWordRepository;

    private final FindingWordAnswerRepository findingWordAnswerRepository;

    private final CatchingWordAnswerRepository catchingWordAnswerRepository;

    public FindingWordStatsDto getFindingWordStats() {

        Map<Long, FindingWordCorrectAnswerDto> idToCorrectAnswer = findingWordRepository.findAllCorrectAnswers()
                .stream()
                .collect(Collectors.toMap(FindingWordCorrectAnswerDto::getFindingWordId, Function.identity()));
        List<FindingWordAnswer> answers = findingWordAnswerRepository.findAll();
        Map<Long, Map<UiMode, FindingWordStatsDto.StatsPerQuestionDto>> idUiModeToStatsPerQuestion = idToCorrectAnswer
                .keySet()
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(), id -> Arrays.stream(UiMode.values()).collect(Collectors.toMap(
                                Function.identity(), uiMode -> new FindingWordStatsDto.StatsPerQuestionDto()))));
        idUiModeToStatsPerQuestion.keySet().forEach(id ->
                idUiModeToStatsPerQuestion.get(id).keySet().forEach(uiMode -> {
                    FindingWordStatsDto.StatsPerQuestionDto statsDto = idUiModeToStatsPerQuestion.get(id).get(uiMode);
                    statsDto.setFindingWordId(id);
                    statsDto.setUiMode(uiMode);
                }));
        answers.forEach(answer -> {
            long findingWordId = answer.getFindingWord().getId();
            FindingWordStatsDto.StatsPerQuestionDto stats =
                    idUiModeToStatsPerQuestion.get(findingWordId).get(answer.getUiMode());
            stats.setTotalNum(stats.getTotalNum() + 1);
            stats.setCorrectNum(stats.getCorrectNum() + (Objects.equals(
                    answer.getAnswer(), idToCorrectAnswer.get(findingWordId).getCorrectAnswer()) ? 1 : 0));
            stats.setTotalSeconds(stats.getTotalSeconds() + answer.getEstimatedSeconds());
        });

        FindingWordStatsDto findingWordStatsDto = new FindingWordStatsDto();
        ArrayList<FindingWordStatsDto.StatsPerQuestionDto> statsPerQuestionDtos = new ArrayList<>();
        idUiModeToStatsPerQuestion.values().forEach(uiModeToStatsPerQuestion ->
                statsPerQuestionDtos.addAll(uiModeToStatsPerQuestion.values()));
        findingWordStatsDto.setStatsPerQuestion(statsPerQuestionDtos);
        findingWordStatsDto.setTotalNum(statsPerQuestionDtos.stream()
                .mapToLong(FindingWordStatsDto.StatsPerQuestionDto::getTotalNum).sum());
        findingWordStatsDto.setCorrectNum(statsPerQuestionDtos.stream()
                .mapToLong(FindingWordStatsDto.StatsPerQuestionDto::getCorrectNum).sum());
        findingWordStatsDto.setTotalSeconds(statsPerQuestionDtos.stream()
                .mapToLong(FindingWordStatsDto.StatsPerQuestionDto::getTotalSeconds).sum());

        return findingWordStatsDto;
    }
}
