package sejong.hci.darkmodesurveybackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sejong.hci.darkmodesurveybackend.dto.catcingword.CatchingWordAnswerDto;
import sejong.hci.darkmodesurveybackend.dto.catcingword.CatchingWordQuestionDto;
import sejong.hci.darkmodesurveybackend.dto.common.ApiResult;
import sejong.hci.darkmodesurveybackend.service.CatchingWordService;

import java.util.List;

@RestController
@RequestMapping("/catching-word")
@RequiredArgsConstructor
public class CatchingWordController {

    private final CatchingWordService catchingWordService;

    @GetMapping
    public ResponseEntity<ApiResult<List<CatchingWordQuestionDto>>> getCatchingWordQuestions() {
        ApiResult<List<CatchingWordQuestionDto>> result = ApiResult.<List<CatchingWordQuestionDto>>builder()
                .data(catchingWordService.getQuestions())
                .build();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ApiResult<Void>> saveCatchingWordAnswers(@RequestBody List<CatchingWordAnswerDto> answers) {

        catchingWordService.saveAnswers(answers);

        ApiResult<Void> result = ApiResult.<Void>builder().build();

        return ResponseEntity.ok(result);
    }
}
