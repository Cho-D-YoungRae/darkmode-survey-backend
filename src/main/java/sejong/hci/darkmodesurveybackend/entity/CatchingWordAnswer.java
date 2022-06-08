package sejong.hci.darkmodesurveybackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "catching_word_answer")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class CatchingWordAnswer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catching_word_answer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catching_word_id", nullable = false)
    private CatchingWord catchingWord;

    @Column(name = "answer", nullable = false, length = 45)
    private String answer;

    @Column(name = "ui_mode", nullable = false, length = 25)
    @Enumerated(EnumType.STRING)
    private UiMode uiMode;

    @Column(name = "created_by", nullable = false, updatable = false, length = 45)
    private String createdBy;
}
