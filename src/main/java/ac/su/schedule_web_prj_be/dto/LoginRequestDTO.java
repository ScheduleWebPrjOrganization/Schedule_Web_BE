package ac.su.schedule_web_prj_be.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String name;
    private String pwd;
}
