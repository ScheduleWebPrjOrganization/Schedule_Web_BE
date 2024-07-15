//package ac.su.schedule_web_prj_be;
//
//import ac.su.schedule_web_prj_be.domain.Member;
//import ac.su.schedule_web_prj_be.domain.Subject;
//import ac.su.schedule_web_prj_be.domain.Task;
//import ac.su.schedule_web_prj_be.domain.TaskStatus;
//import ac.su.schedule_web_prj_be.service.MemberService;
//import ac.su.schedule_web_prj_be.service.SubjectService;
//import ac.su.schedule_web_prj_be.service.TaskService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class SubjectTaskIntegrationTest {
//
//    @Autowired
//    private SubjectService subjectService;
//
//    @Autowired
//    private TaskService taskService;
//
//    @Autowired
//    private MemberService memberService;
//
//    @Test
//    @Transactional
//    public void testAddTaskToSubject() {
//        // Given
//        Member member = new Member();
//        member.setPwd("password");
//        member.setEmail("test@example.com");
//        member.setCreatedAt(LocalDateTime.now());
//        member.setUserType("user");
//        member.setLevel("beginner");
//        Member savedMember = memberService.createMember(member); // Member 저장
//
//        Subject subject = subjectService.addSubject(savedMember.getId(), "Test Subject");
//
//        Task task = new Task();
//        task.setName("Test Task");
//        task.setStatus(TaskStatus.IN_PROGRESS);
//        task.setHoursToComplete(5);
//
//        // When
//        task.setSubject(subject);
//        Task createdTask = taskService.createTask(task);
//
//        // Then
//        Task fetchedTask = taskService.getTaskById(createdTask.getId());
//        assertEquals(subject.getId(), fetchedTask.getSubject().getId());
//    }
//}
