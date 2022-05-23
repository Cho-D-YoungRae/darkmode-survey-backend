package sejong.hci.darkmodesurveybackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sejong.hci.darkmodesurveybackend.dto.common.ApiResult;
import sejong.hci.darkmodesurveybackend.dto.findingword.FindingWordQuestionsDto;
import sejong.hci.darkmodesurveybackend.service.FindingWordService;

import java.util.List;

@RestController
@RequestMapping("/finding-word")
@RequiredArgsConstructor
public class FindingWordController {

    private final FindingWordService findingWordService;

    @GetMapping
    public ResponseEntity<ApiResult<List<FindingWordQuestionsDto>>> getFindingWordQuestions() {

        ApiResult<List<FindingWordQuestionsDto>> result = ApiResult.<List<FindingWordQuestionsDto>>builder()
                .data(findingWordService.getQuestions())
                .build();

        return ResponseEntity.ok(result);
    }
}
