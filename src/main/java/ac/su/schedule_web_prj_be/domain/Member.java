package ac.su.schedule_web_prj_be.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "pwd", nullable = false)
    private String pwd;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "user_type", nullable = false)
    private String userType;

    @Column(name = "group_joined_at")
    private Date group_joined_at;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private StudyGroup studyGroup;

    @Column(name = "level", nullable = false)
    private String level;

    @OneToMany(mappedBy = "member")
    private List<GroupChat> groupChats;

    @OneToMany(mappedBy = "member")
    private List<Task> tasks;

    @OneToMany(mappedBy = "member")
    private List<Statistic> statistics;

    @OneToMany(mappedBy = "member")
    private List<Subject> subjects;

    public Member() {
        this.id = UUID.randomUUID().toString();
    }
}
