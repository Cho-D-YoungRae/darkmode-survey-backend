package sejong.hci.darkmodesurveybackend.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.hci.darkmodesurveybackend.dto.findingword.FindingWordAnswerDto;
import sejong.hci.darkmodesurveybackend.dto.findingword.FindingWordQuestionsDto;
import sejong.hci.darkmodesurveybackend.entity.FindingWord;
import sejong.hci.darkmodesurveybackend.entity.FindingWordAnswer;
import sejong.hci.darkmodesurveybackend.repository.FindingWordAnswerRepository;
import sejong.hci.darkmodesurveybackend.repository.FindingWordRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindingWordService {

    private final FindingWordRepository findingWordRepository;

    private final FindingWordAnswerRepository findingWordAnswerRepository;

    private final ModelMapper modelMapper;

    public List<FindingWordQuestionsDto> getQuestions() {
        return findingWordRepository.findAll().stream()
                .map(findingWord -> modelMapper.map(findingWord, FindingWordQuestionsDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Long> saveAnswers(List<FindingWordAnswerDto> answerDtos) {
        return answerDtos.stream().map(answerDto -> findingWordAnswerRepository.save(FindingWordAnswer.builder()
                        .findingWord(FindingWord.builder().id(answerDto.getFindingWordId()).build())
                        .answer(answerDto.getAnswer())
                        .estimatedSeconds(answerDto.getEstimatedSeconds())
                        .build()).getId())
                .collect(Collectors.toList());
    }
}
