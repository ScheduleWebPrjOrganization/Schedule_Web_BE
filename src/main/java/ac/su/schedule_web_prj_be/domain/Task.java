package ac.su.schedule_web_prj_be.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private TaskStatus status;

    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate createdAt;

    @JsonIgnore
    @Column(name = "member_id", insertable = false, updatable = false)
    private String memberId;

    @Column(name = "hours_to_complete", nullable = false)
    private int hoursToComplete;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Task() {
        this.createdAt = LocalDate.now();
    }

    public Task(String name, TaskStatus status, int hoursToComplete, Member member, Subject subject) {
        this.name = name;
        this.status = status;
        this.createdAt = LocalDate.now();
        this.hoursToComplete = hoursToComplete;
        this.subject = subject;
    }

    public void setPriority(int i) {
    }
}
