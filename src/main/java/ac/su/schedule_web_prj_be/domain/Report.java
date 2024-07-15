package ac.su.schedule_web_prj_be.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat id", nullable = false)
    private GroupChat groupChat;

    @Column(name = "reported_user_id",nullable = false)
    private String reportedUserId;

    @Column(name = "reporter_id",nullable = false)
    private String reporterId;

    @Column(nullable = false)
    private String status;

    @Column(name = "created_at",nullable = false)
    private Date createdAt;
}
