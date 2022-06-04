package sejong.hci.darkmodesurveybackend.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sejong.hci.darkmodesurveybackend.entity.CatchingWordAnswer;

import java.util.List;

public interface CatchingWordAnswerRepository extends JpaRepository<CatchingWordAnswer, Long> {

    @Query("select answer from CatchingWordAnswer answer join fetch answer.catchingWord")
    List<CatchingWordAnswer> findAllWithCatchingWord();
}
