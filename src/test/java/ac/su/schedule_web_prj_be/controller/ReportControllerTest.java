//package ac.su.schedule_web_prj_be.controller;
//
//import ac.su.schedule_web_prj_be.domain.Report;
//import ac.su.schedule_web_prj_be.domain.ReportStatus;
//import ac.su.schedule_web_prj_be.service.ReportService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Date;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//// HTTP 요청/응답의 처리, 엔드포인트 매핑, 서비스 계층과의 통합, 에러 처리 등을 검증하는
//// Controller 에 대한 테스트 코드
//
//// 레포지터리 테스트: 데이터베이스와의 상호작용을 검증합니다. CRUD 작업과 쿼리의 정확성을 테스트합니다.
//// 서비스 테스트: 비즈니스 로직을 검증합니다. 서비스 계층의 메서드가 올바르게 동작하는지 확인합니다.
//// 컨트롤러 테스트: 웹 계층을 검증합니다. HTTP 요청과 응답이 예상대로 처리되는지 확인하고, 서비스 계층과의 상호작용을 테스트합니다.
//
//// 원래 service 만 만들었다가 inClass-spring-security 테스트 코드에서 RepoTest 도 있길래
//// 어떤 테스트가 있는지 GPT 에 검색 한 내용 -> ControllerTest 생성
//// 내가 생각한 장점 : 굳이 HTML 같은 페이지 없이 API 만 테스트하고 싶을 때 사용 가능
//
//// @WebMvcTest: MVC 컨트롤러 테스트에 사용되는 애노테이션
//// @WebMvcTest 어노테이션을 사용하면 스프링 MVC 인프라를 사용하여 컨트롤러를 테스트할 수 있습니다.
//// @WebMvcTest 는 컨트롤러 테스트에 필요한 빈만 등록하기 때문에, 컨트롤러 테스트를 빠르게 수행할 수 있습니다.
//// 위 3줄은 Copilot 자동 완성 주석
//
//@WebMvcTest(ReportController.class)
//class ReportControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ReportService reportService;
//
//    private Report report;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        report = new Report();
//        report.setId(1L);
//        report.setReportedUserId("reportedUser");
//        report.setReporterId("reporter");
//        report.setStatus(ReportStatus.BAN.toString());
//        report.setCreatedAt(new Date());
//    }
//
//    @Test
//    @DisplayName("신고 생성 테스트")
//    public void testCreateReport() throws Exception {
//        when(reportService.createReport(any(Report.class))).thenReturn(report);
//
//        mockMvc.perform(post("/api/reports")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"reportedUserId\":\"reportedUser\", \"reporterId\":\"reporter\"}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(report.getId()))
//                .andExpect(jsonPath("$.reportedUserId").value(report.getReportedUserId()))
//                .andExpect(jsonPath("$.reporterId").value(report.getReporterId()));
//    }
//
//    @Test
//    @DisplayName("신고 조회 테스트")
//    public void testGetReport() throws Exception {
//        when(reportService.getReport(1L)).thenReturn(Optional.of(report));
//
//        mockMvc.perform(get("/api/reports/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(report.getId()))
//                .andExpect(jsonPath("$.reportedUserId").value(report.getReportedUserId()))
//                .andExpect(jsonPath("$.reporterId").value(report.getReporterId()));
//    }
//
//    @Test
//    @DisplayName("모든 신고 조회 테스트")
//    public void testUpdateReportStatus() throws Exception {
//        when(reportService.updateReportStatus(1L, ReportStatus.BAN)).thenReturn(report);
//
//        mockMvc.perform(put("/api/reports/1/status")
//                        .param("status", "BAN"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(ReportStatus.BAN.name()));
//    }
//}
