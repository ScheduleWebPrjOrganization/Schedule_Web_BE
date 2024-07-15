//package ac.su.schedule_web_prj_be.service;
//
//import ac.su.schedule_web_prj_be.domain.Report;
//import ac.su.schedule_web_prj_be.domain.ReportStatus;
//import ac.su.schedule_web_prj_be.repository.ReportRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class ReportServiceTest {
//
//    // Mock 객체 - 가짜 객체 -> Stubbing 해야함.
//    // 그냥 호출 시 primitive type 은 0, 참조형은 null 을 반환
//
//    // @Spy mock - 진짜 객체
//    // 메소드 실행 시 스터빙을 하지 않으면 기존 객체의 로직을 실행한 값을, 스터빙을 한 경우엔 스터빙 값을 리턴
//
//    // @Mock 이나 @Spy 로 생성된 mock 객체를 자동으로 주입해주는 어노테이션
//
//    // 즉 단위 테스트 시에는 Mock 객체를 사용하여 외부 의존성을 제거하고 테스트를 진행할 수 있음
//    // 기존 @AutoWired 로 주입받는 객체를 Mock 객체로 대체해서 테스트를 테스트해봄..
//    // 참고로 @AutoWired 는 실제 Bean 을 주입함.
//
//    // 주석에 대한 정보 : https://effortguy.tistory.com/142
//
//    @InjectMocks
//    private ReportService reportService;
//
//    @Mock
//    private ReportRepository reportRepository;
//
//    private Report report;
//
//    // 주석 생략 -> controller, repository 에서의 주석을 보면 이해 가능할 거라 믿음.
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        report = new Report();
//        report.setId(1L);
//        report.setReportedUserId("reportedUser");
//        report.setReporterId("reporter");
//        report.setStatus(ReportStatus.WAITING.toString());
//        report.setCreatedAt(new Date());
//    }
//
//    @Test
//    @DisplayName("Create Report")
//    public void testCreateReport() {
//        when(reportRepository.save(any(Report.class))).thenReturn(report);
//
//        Report createdReport = reportService.createReport(report);
//
//        assertNotNull(createdReport);
//        assertEquals(report.getId(), createdReport.getId());
//        verify(reportRepository, times(1)).save(report);
//    }
//
//    @Test
//    @DisplayName("Get Report")
//    public void testGetReport() {
//        when(reportRepository.findById(1L)).thenReturn(Optional.of(report));
//
//        Optional<Report> foundReport = reportService.getReport(1L);
//
//        assertTrue(foundReport.isPresent());
//        assertEquals(report.getId(), foundReport.get().getId());
//        verify(reportRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    @DisplayName("Get All Reports")
//    public void testGetAllReports() {
//        List<Report> reports = new ArrayList<>();
//        reports.add(report);
//        when(reportRepository.findAll()).thenReturn(reports);
//
//        List<Report> foundReports = reportService.getAllReports();
//
//        assertFalse(foundReports.isEmpty());
//        assertEquals(1, foundReports.size());
//        verify(reportRepository, times(1)).findAll();
//    }
//
//    @Test
//    @DisplayName("Get Reports By GroupChatId")
//    public void testGetReportsByGroupChatId() {
//        List<Report> reports = new ArrayList<>();
//        reports.add(report);
//        when(reportRepository.findByGroupChatId(1L)).thenReturn(reports);
//
//        List<Report> foundReports = reportService.getReportsByGroupChatId(1L);
//
//        assertFalse(foundReports.isEmpty());
//        assertEquals(1, foundReports.size());
//        verify(reportRepository, times(1)).findByGroupChatId(1L);
//    }
//
//    @Test
//    @DisplayName("Get Reports By ReporterId")
//    public void testGetReportsByReporterId() {
//        List<Report> reports = new ArrayList<>();
//        reports.add(report);
//        when(reportRepository.findByReporterId("reporter")).thenReturn(reports);
//
//        List<Report> foundReports = reportService.getReportsByReporterId("reporter");
//
//        assertFalse(foundReports.isEmpty());
//        assertEquals(1, foundReports.size());
//        verify(reportRepository, times(1)).findByReporterId("reporter");
//    }
//
//    @Test
//    @DisplayName("Get Reports By ReportedUserId")
//    public void testGetReportsByReportedUserId() {
//        List<Report> reports = new ArrayList<>();
//        reports.add(report);
//        when(reportRepository.findByReportedUserId("reportedUser")).thenReturn(reports);
//
//        List<Report> foundReports = reportService.getReportsByReportedUserId("reportedUser");
//
//        assertFalse(foundReports.isEmpty());
//        assertEquals(1, foundReports.size());
//        verify(reportRepository, times(1)).findByReportedUserId("reportedUser");
//    }
//
//    @Test
//    public void testGetReportsByStatus() {
//        List<Report> reports = new ArrayList<>();
//        reports.add(report);
//        when(reportRepository.findByStatus(ReportStatus.WAITING)).thenReturn(reports);
//
//        List<Report> foundReports = reportService.getReportsByStatus(ReportStatus.WAITING);
//
//        assertFalse(foundReports.isEmpty());
//        assertEquals(1, foundReports.size());
//        verify(reportRepository, times(1)).findByStatus(ReportStatus.WAITING);
//    }
//
//    @Test
//    public void testUpdateReportStatus() {
//        when(reportRepository.findById(1L)).thenReturn(Optional.of(report));
//        when(reportRepository.save(any(Report.class))).thenReturn(report);
//
//        Report updatedReport = reportService.updateReportStatus(1L, ReportStatus.BAN);
//
//        assertNotNull(updatedReport);
//        assertEquals(ReportStatus.BAN, updatedReport.getStatus());
//        verify(reportRepository, times(1)).findById(1L);
//        verify(reportRepository, times(1)).save(report);
//    }
//}
