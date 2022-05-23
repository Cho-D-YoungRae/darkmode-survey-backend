package sejong.hci.darkmodesurveybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.hci.darkmodesurveybackend.entity.CatchingWordAnswer;

public interface CatchingWordAnswerRepository extends JpaRepository<CatchingWordAnswer, Long> {
}
