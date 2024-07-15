package ac.su.schedule_web_prj_be.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "id")
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

//    @Column(name = "learning_now")  // 현재 공부중인 것을 표시하기 위한 컬럼
//    private boolean learning_now;

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
}
