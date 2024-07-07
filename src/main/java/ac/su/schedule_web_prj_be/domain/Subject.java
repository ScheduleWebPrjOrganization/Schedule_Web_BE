package ac.su.schedule_web_prj_be.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Statistic> statistics;

    @OneToMany(mappedBy = "subject")
    private List<Task> tasks;

    @OneToMany(mappedBy = "subject")
    private List<SubjectRecord> subjectRecords;
}
