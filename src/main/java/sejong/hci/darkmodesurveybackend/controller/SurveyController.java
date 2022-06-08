package sejong.hci.darkmodesurveybackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sejong.hci.darkmodesurveybackend.dto.common.ApiResult;
import sejong.hci.darkmodesurveybackend.dto.survey.UiPreferenceSurveyAnswerDto;
import sejong.hci.darkmodesurveybackend.service.SurveyService;

import javax.validation.Valid;

@RestController
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping("/ui-mode")
    public ResponseEntity<ApiResult<Void>> saveUiPreferenceSurveyAnswer(
            @Valid @RequestBody UiPreferenceSurveyAnswerDto answerDto) {

        surveyService.saveUiPreferenceSurveyAnswer(answerDto);

        ApiResult<Void> result = ApiResult.<Void>builder().build();

        return ResponseEntity.ok(result);
    }
}
