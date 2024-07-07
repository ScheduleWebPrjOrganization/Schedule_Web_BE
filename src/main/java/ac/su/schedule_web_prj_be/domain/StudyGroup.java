package ac.su.schedule_web_prj_be.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "study_group")
public class StudyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "member_count", nullable = false)
    private Integer memberCount;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "studyGroup")
    private List<Member> members;

    @OneToMany(mappedBy = "studyGroup")
    private List<GroupChat> groupChats;
}
