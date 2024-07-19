package ac.su.schedule_web_prj_be.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "subject_record")
public class SubjectRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recorded_at", nullable = false)
    private Date recordedAt;

    @Column(name = "stopped_at", nullable = false)
    private Date stoppedAt;

    @Column(name = "paused_at")  // 일시정지 기능입니다.
    private Date pausedAt;

    @Column(name = "paused_duration")  // 일시정지한 시간을 계산하는 기능입니다.
    private long pausedDuration = 0;

    @ManyToOne
//    @JoinColumn(name = "subject_id", nullable = false)
    @JoinColumn(name = "subject_id") // 타이머 시간만이 DB에 저장되는지 확인하기 위해 변경했습니다.
    private Subject subject;
}
