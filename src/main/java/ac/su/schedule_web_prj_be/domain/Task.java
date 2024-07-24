package ac.su.schedule_web_prj_be.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatus status = TaskStatus.NOT_DONE;

    @Column(name = "hours_to_complete", nullable = false)
    private int hoursToComplete;


    @Column(name = "actual_hours", nullable = true)
    private int actualHours = 0;

    @Column(name = "dateKey", nullable = false)
    private String dateKey;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)  // member_id를 외래키로 설정
    @JsonIgnore
    private Member member;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonIgnore
    private Subject subject;

    public Task() {}

    public Task(String name, TaskStatus status, int hoursToComplete, String dateKey, Member member, Subject subject) {
        this.name = name;
        this.status = status;
        this.member = member;
        this.dateKey = dateKey;
        this.hoursToComplete = hoursToComplete;
        this.actualHours = 0; // 기본값 설정
        this.subject = subject;
    }

    public void setPriority(int i) {
    }
}
