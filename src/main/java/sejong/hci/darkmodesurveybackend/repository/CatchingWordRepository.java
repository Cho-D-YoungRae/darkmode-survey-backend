package sejong.hci.darkmodesurveybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.hci.darkmodesurveybackend.entity.CatchingWord;

public interface CatchingWordRepository extends JpaRepository<CatchingWord, Long> {
}
