package ac.su.schedule_web_prj_be.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
public class RegisterRequestDTO {
    public String id;
    public String pwd;
    public String verifyingPwd;
    public String email;
    public LocalDateTime createdAt;
}
