package sejong.hci.darkmodesurveybackend.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.hci.darkmodesurveybackend.dto.findingword.FindingWordAnswerDto;
import sejong.hci.darkmodesurveybackend.dto.findingword.FindingWordQuestionDto;
import sejong.hci.darkmodesurveybackend.entity.FindingWord;
import sejong.hci.darkmodesurveybackend.entity.FindingWordAnswer;
import sejong.hci.darkmodesurveybackend.repository.FindingWordAnswerRepository;
import sejong.hci.darkmodesurveybackend.repository.FindingWordRepository;
import sejong.hci.darkmodesurveybackend.dto.findingword.FindingWordCorrectAnswerDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindingWordService {

    private final FindingWordRepository findingWordRepository;

    private final FindingWordAnswerRepository findingWordAnswerRepository;

    private final ModelMapper modelMapper;

    public List<FindingWordQuestionDto> getQuestions() {
        return findingWordRepository.findAll().stream()
                .map(findingWord -> modelMapper.map(findingWord, FindingWordQuestionDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Long> saveAnswers(List<FindingWordAnswerDto> answerDtos) {
        String createdBy = UUID.randomUUID().toString();
        return findingWordAnswerRepository.saveAll(answerDtos.stream().map(answerDto -> FindingWordAnswer.builder()
                                .findingWord(FindingWord.builder().id(answerDto.getFindingWordId()).build())
                                .answer(answerDto.getAnswer())
                                .uiMode(answerDto.getUiMode())
                                .estimatedSeconds(answerDto.getEstimatedSeconds())
                                .createdBy(createdBy)
                                .build())
                        .collect(Collectors.toList()))
                .stream()
                .map(FindingWordAnswer::getId)
                .collect(Collectors.toList());
    }

    public List<FindingWordCorrectAnswerDto> getCorrectAnswers() {
        return findingWordRepository.findAllCorrectAnswers();
    }
}
