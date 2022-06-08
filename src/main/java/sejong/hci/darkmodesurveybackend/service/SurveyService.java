package sejong.hci.darkmodesurveybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.hci.darkmodesurveybackend.dto.survey.UiPreferenceSurveyAnswerDto;
import sejong.hci.darkmodesurveybackend.entity.UiPreferenceSurvey;
import sejong.hci.darkmodesurveybackend.repository.UiPreferenceSurveyRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyService {

    private final UiPreferenceSurveyRepository uiPreferenceSurveyRepository;

    @Transactional
    public long saveUiPreferenceSurveyAnswer(UiPreferenceSurveyAnswerDto answerDto) {
        UiPreferenceSurvey uiPreferenceSurvey = UiPreferenceSurvey.builder()
                .uiMode(answerDto.getPreferredUiMode())
                .build();
        return uiPreferenceSurveyRepository.save(uiPreferenceSurvey).getId();
    }
}
