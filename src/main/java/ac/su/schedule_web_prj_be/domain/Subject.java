package ac.su.schedule_web_prj_be.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_key", nullable = false)
    private String dateKey;

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private List<Statistic> statistics;

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private List<Task> tasks;

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private List<SubjectRecord> subjectRecords;

    public Subject() {}

    public Subject(String name, String dateKey, Member member) {
        this.name = name;
        this.dateKey = dateKey;
        this.member = member;
    }
}
