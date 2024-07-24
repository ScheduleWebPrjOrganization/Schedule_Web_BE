package ac.su.schedule_web_prj_be.domain;

import ac.su.schedule_web_prj_be.constant.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "pwd", nullable = false)
    private String pwd;

    @Column(name = "email", nullable = false)
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "user_type", nullable = false)
    private String userType;

    @Column(name = "group_joined_at")
    private Date group_joined_at;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonBackReference
    private StudyGroup studyGroup;

    @Column(name = "level", nullable = false)
    private String level;

    @Column(name = "online")
    private boolean online;

    @OneToMany(mappedBy = "member")
    private List<GroupChat> groupChats;

    @OneToMany(mappedBy = "member")
    private List<Task> tasks;

    @OneToMany(mappedBy = "member")
    private List<Statistic> statistics;

    @OneToMany(mappedBy = "member")
    private List<Subject> subjects;


    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.userType == null) {
            this.userType = String.valueOf(UserTypeEnum.User);
        }
    }
}
