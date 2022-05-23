package sejong.hci.darkmodesurveybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.hci.darkmodesurveybackend.entity.FindingWord;

public interface FindingWordRepository extends JpaRepository<FindingWord, Long> {
}
