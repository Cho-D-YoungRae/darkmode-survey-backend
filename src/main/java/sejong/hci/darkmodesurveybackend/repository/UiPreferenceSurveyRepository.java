package sejong.hci.darkmodesurveybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.hci.darkmodesurveybackend.entity.UiPreferenceSurvey;

public interface UiPreferenceSurveyRepository extends JpaRepository<UiPreferenceSurvey, Long> {
}
