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
    @JoinColumn(name = "group_chat_id", nullable = false)
    private GroupChat groupChat;

    @Column(nullable = false)
    private String reportedUserId;

    @Column(nullable = false)
    private String reporterId;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date createdAt;
}
