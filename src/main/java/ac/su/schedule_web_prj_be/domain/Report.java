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

    @Column(nullable = false)
    private String reported_user_id;

    @Column(nullable = false)
    private String reporter_id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date created_at;
}
