package ac.su.schedule_web_prj_be.repository;

import ac.su.schedule_web_prj_be.domain.GroupChat;
import ac.su.schedule_web_prj_be.domain.Report;
import ac.su.schedule_web_prj_be.domain.ReportStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
//@Transactional - 그냥 application.properties 의 localhost db 를 사용해 테스트 진행해서 안넣음.
public class ReportRepositoryTest {

    // Repo 주입
    // @Autowired: 스프링 IoC 컨테이너에 의해 관리되는 Bean 을 주입하는데 사용되는 어노테이션
    // 실제 빈을 주입받아 전체적인 동작을 테스트할 때 유용
    // => 통합 테스트와 실제 애플리케이션 실행 시 사용

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private GroupChatRepository groupChatRepository;

    private Report report;
    private GroupChat groupChat;

    // @BeforeEach: 각 테스트 메소드가 실행되기 전에 실행되는 메소드
    // 실습 때 진행했던 create() 테스트 메서드를 만든 후
    // 각 테스트 마다 create() 메서드를 넣은 것과 동일한 결과.
    // 그룹 채팅과 신고를 생성하고 저장
    @BeforeEach
    public void setUp() {
        groupChat = new GroupChat();
        groupChat.setSentAt(new Timestamp(System.currentTimeMillis()));
        groupChat.setContent("Test message content"); // content 필드에 값 설정
        groupChatRepository.save(groupChat);

        report = new Report();
        report.setGroupChat(groupChat);
        report.setReportedUserId("reportedUser");
        report.setReporterId("reporter");
        report.setStatus(ReportStatus.WAITING);
        report.setCreatedAt(new Date());
        reportRepository.save(report);
    }

    @Test
    @DisplayName("그룹 채팅 ID로 검색")
    public void testFindByGroupChatId() {
        List<Report> reports = reportRepository.findByGroupChatId(groupChat.getId());
//        assert !reports.isEmpty();   // = assertFalse(reports.isEmpty());
        assertFalse(reports.isEmpty());
        assertEquals(1, reports.size());
        assertEquals(report.getId(), reports.get(0).getId());
    }

    @Test
    @DisplayName("신고자로 검색")
    public void testFindByReporterId() {
        List<Report> reports = reportRepository.findByReporterId("reporter");
        assert !reports.isEmpty();
        assertEquals(1, reports.size());
        assertEquals(report.getId(), reports.get(0).getId());
    }

    @Test
    @DisplayName("신고대상으로 검색")
    public void testFindByReportedUserId() {
        List<Report> reports = reportRepository.findByReportedUserId("reportedUser");
        assert !reports.isEmpty();
        assertEquals(1, reports.size());
        assertEquals(report.getId(), reports.get(0).getId());
    }

    @Test
    @DisplayName("처리 상태로 검색")
    public void testFindByStatus() {
        List<Report> reports = reportRepository.findByStatus(ReportStatus.WAITING);
        assert !reports.isEmpty();
        assertEquals(1, reports.size());
        assertEquals(report.getId(), reports.get(0).getId());
    }

    @Test
    @DisplayName("최신순 조회")
    public void testFindAllByOrderByCreatedAtDesc() {
        List<Report> reports = reportRepository.findAllByOrderByCreatedAtDesc();
        assert !reports.isEmpty();
        assertEquals(1, reports.size());
        assertEquals(report.getId(), reports.get(0).getId());
    }

    @Test
    @DisplayName("날짜로 조회 (특정 날짜 이후)")
    public void testFindByCreatedAtAfter() {
        Date date = new Date(System.currentTimeMillis() - 1000); // 1초 전
        List<Report> reports = reportRepository.findByCreatedAtAfter(date);
        assert !reports.isEmpty();
        assertEquals(1, reports.size());
        assertEquals(report.getId(), reports.get(0).getId());
    }

    @Test
    @DisplayName("날짜로 조회 (특정 날짜 범위)")
    public void testFindByCreatedAtBetween() {
        Date startDate = new Date(System.currentTimeMillis() - 1000); // 1초 전
        Date endDate = new Date(System.currentTimeMillis() + 1000); // 1초 후
        List<Report> reports = reportRepository.findByCreatedAtBetween(startDate, endDate);
        assert !reports.isEmpty();
        assertEquals(1, reports.size());
        assertEquals(report.getId(), reports.get(0).getId());
    }

    @Test
    @DisplayName("신고 ID로 검색")
    public void testSaveAndFindById() {
        Optional<Report> foundReport = reportRepository.findById(report.getId());
        assertTrue(foundReport.isPresent());
        assertEquals(report.getId(), foundReport.get().getId());
    }
}
