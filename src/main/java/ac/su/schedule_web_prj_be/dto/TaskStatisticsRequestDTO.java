package ac.su.schedule_web_prj_be.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskStatisticsRequestDTO {
    private Long memberId;
    private String startDate;
    private String endDate;
}