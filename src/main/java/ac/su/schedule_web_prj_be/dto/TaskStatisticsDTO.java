package ac.su.schedule_web_prj_be.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatisticsDTO {
    private String subjectName;
    private int totalHours;

    public TaskStatisticsDTO(String subjectName, int totalHours) {
        this.subjectName = subjectName;
        this.totalHours = totalHours;
    }
}