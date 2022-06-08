package sejong.hci.darkmodesurveybackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sejong.hci.darkmodesurveybackend.dto.catcingword.CatchingWordAnswerDto;
import sejong.hci.darkmodesurveybackend.dto.catcingword.CatchingWordAnswersDto;
import sejong.hci.darkmodesurveybackend.dto.catcingword.CatchingWordDto;
import sejong.hci.darkmodesurveybackend.dto.catcingword.CatchingWordGradeDto;
import sejong.hci.darkmodesurveybackend.dto.common.ApiResult;
import sejong.hci.darkmodesurveybackend.service.CatchingWordService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/catching-word")
@RequiredArgsConstructor
public class CatchingWordController {

    private final CatchingWordService catchingWordService;

    @GetMapping
    public ResponseEntity<ApiResult<List<CatchingWordDto>>> getCatchingWordQuestions() {
        ApiResult<List<CatchingWordDto>> result = ApiResult.<List<CatchingWordDto>>builder()
                .data(catchingWordService.getCatchingWords())
                .build();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ApiResult<CatchingWordGradeDto>> saveCatchingWordAnswers(
            @Valid @RequestBody CatchingWordAnswersDto answersDto) {

        List<CatchingWordAnswerDto> answers = answersDto.getAnswers();
        catchingWordService.saveAnswers(answers);

        List<CatchingWordDto> catchingWords = catchingWordService.getCatchingWords();

        CatchingWordGradeDto grade = new CatchingWordGradeDto(answers, catchingWords);

        ApiResult<CatchingWordGradeDto> result = ApiResult.<CatchingWordGradeDto>builder()
                .data(grade)
                .build();

        return ResponseEntity.ok(result);
    }
}
