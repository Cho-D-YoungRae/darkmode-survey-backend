package sejong.hci.darkmodesurveybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sejong.hci.darkmodesurveybackend.entity.FindingWord;
import sejong.hci.darkmodesurveybackend.repository.dto.FindingWordCorrectAnswerDto;

import java.util.List;

public interface FindingWordRepository extends JpaRepository<FindingWord, Long> {

    @Query("select new sejong.hci.darkmodesurveybackend.repository.dto.FindingWordCorrectAnswerDto(" +
            "fw.id, fw.correctAnswer)" +
            " from FindingWord fw")
    List<FindingWordCorrectAnswerDto> findAllCorrectAnswers();
}
