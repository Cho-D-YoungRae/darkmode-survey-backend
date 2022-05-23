package sejong.hci.darkmodesurveybackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "finding_word_answer")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class FindingWordAnswer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "finding_word_answer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "finding_word_id", nullable = false)
    private FindingWord findingWord;

    @Column(name = "answer", nullable = false)
    private Integer answer;

    @Column(name = "estimated_seconds", nullable = false)
    private Integer estimatedSeconds;
}
