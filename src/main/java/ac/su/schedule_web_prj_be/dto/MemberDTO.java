package ac.su.schedule_web_prj_be.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MemberDTO {
    private Long id;
    private String email;
    private boolean online;
    private List<String> subjects;
}
