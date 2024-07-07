package ac.su.schedule_web_prj_be.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "챗 id", nullable = false)
    private GroupChat groupChat;

    @ManyToOne
    @JoinColumn(name = "회원 id", nullable = false)
    private Member member;
}
