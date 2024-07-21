package ac.su.schedule_web_prj_be.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class StudyGroupDTO {
    private Long id;
    private String name;
    private String description;
    private String createdAt;
    private Integer memberCount;
    private List<MemberDTO> members;
}
