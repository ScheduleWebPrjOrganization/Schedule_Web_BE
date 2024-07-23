package ac.su.schedule_web_prj_be.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TaskDTO {
    @Getter @Setter
    private Long id;
    private String name;
    private String status;
    private String dateKey;
    private List<String> subjects;
}
