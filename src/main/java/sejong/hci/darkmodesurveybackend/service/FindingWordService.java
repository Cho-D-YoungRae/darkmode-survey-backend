package sejong.hci.darkmodesurveybackend.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.hci.darkmodesurveybackend.dto.findingword.FindingWordQuestionsDto;
import sejong.hci.darkmodesurveybackend.repository.FindingWordRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindingWordService {

    private final FindingWordRepository findingWordRepository;

    private final ModelMapper modelMapper;

    public List<FindingWordQuestionsDto> getQuestions() {
        return findingWordRepository.findAll().stream()
                .map(findingWord -> modelMapper.map(findingWord, FindingWordQuestionsDto.class))
                .collect(Collectors.toList());
    }
}
