package ac.su.schedule_web_prj_be.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dateKey", nullable = false)
    private String dateKey;

    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<Statistic> statistics;

    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<Task> tasks;

    @OneToMany(mappedBy = "subject")
    private List<SubjectRecord> subjectRecords;

    public Subject() {}

    public Subject(String name, String dateKey, Member member) {
        this.name = name;
        this.dateKey = dateKey;
        this.member = member;
    }

}
