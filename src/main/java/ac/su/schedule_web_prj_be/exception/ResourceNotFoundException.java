package ac.su.schedule_web_prj_be.exception;

// Spring Rest 예외 처리를 위한 클래스 입니다.
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
