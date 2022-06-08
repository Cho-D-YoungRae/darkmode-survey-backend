package sejong.hci.darkmodesurveybackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sejong.hci.darkmodesurveybackend.dto.common.ApiResult;
import sejong.hci.darkmodesurveybackend.dto.findingword.FindingWordAnswerDto;
import sejong.hci.darkmodesurveybackend.dto.findingword.FindingWordAnswersDto;
import sejong.hci.darkmodesurveybackend.dto.findingword.FindingWordGradeDto;
import sejong.hci.darkmodesurveybackend.dto.findingword.FindingWordQuestionDto;
import sejong.hci.darkmodesurveybackend.dto.findingword.FindingWordCorrectAnswerDto;
import sejong.hci.darkmodesurveybackend.service.FindingWordService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/finding-word")
@RequiredArgsConstructor
public class FindingWordController {

    private final FindingWordService findingWordService;

    @GetMapping
    public ResponseEntity<ApiResult<List<FindingWordQuestionDto>>> getFindingWordQuestions() {

        ApiResult<List<FindingWordQuestionDto>> result = ApiResult.<List<FindingWordQuestionDto>>builder()
                .data(findingWordService.getQuestions())
                .build();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ApiResult<FindingWordGradeDto>> saveFindingWordAnswers(
            @Valid @RequestBody FindingWordAnswersDto answersDto) {

        List<FindingWordAnswerDto> answers = answersDto.getAnswers();
        findingWordService.saveAnswers(answers);

        List<FindingWordCorrectAnswerDto> correctAnswers = findingWordService.getCorrectAnswers();

        FindingWordGradeDto grade = new FindingWordGradeDto(answers, correctAnswers);

        ApiResult<FindingWordGradeDto> result = ApiResult.<FindingWordGradeDto>builder()
                .data(grade)
                .build();

        return ResponseEntity.ok(result);
    }
}
