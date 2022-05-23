package sejong.hci.darkmodesurveybackend.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.hci.darkmodesurveybackend.dto.catcingword.CatchingWordAnswerDto;
import sejong.hci.darkmodesurveybackend.dto.catcingword.CatchingWordQuestionDto;
import sejong.hci.darkmodesurveybackend.entity.CatchingWord;
import sejong.hci.darkmodesurveybackend.entity.CatchingWordAnswer;
import sejong.hci.darkmodesurveybackend.repository.CatchingWordAnswerRepository;
import sejong.hci.darkmodesurveybackend.repository.CatchingWordRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CatchingWordService {

    private final CatchingWordRepository catchingWordRepository;

    private final CatchingWordAnswerRepository catchingWordAnswerRepository;

    private final ModelMapper modelMapper;

    public List<CatchingWordQuestionDto> getQuestions() {
        return catchingWordRepository.findAll().stream()
                .map(catchingWord -> modelMapper.map(catchingWord, CatchingWordQuestionDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Long> saveAnswers(List<CatchingWordAnswerDto> answerDtos) {
        return answerDtos.stream().map(answerDto -> catchingWordAnswerRepository.save(CatchingWordAnswer.builder()
                        .catchingWord(CatchingWord.builder().id(answerDto.getCatchingWordId()).build())
                        .answer(answerDto.getAnswer())
                        .uiMode(answerDto.getUiMode())
                        .build()).getId())
                .collect(Collectors.toList());
    }
}
