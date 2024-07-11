package ac.su.schedule_web_prj_be.controller;

import ac.su.schedule_web_prj_be.domain.Report;
import ac.su.schedule_web_prj_be.domain.ReportStatus;
import ac.su.schedule_web_prj_be.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // Report Entity 에 대한 Post 요청 - Create Report
    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        Report createdReport = reportService.createReport(report);
        return ResponseEntity.ok(createdReport);
    }

    // 신고 ID 에 대한 Get 요청 - Get Report
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReport(@PathVariable Long id) {
        Optional<Report> report = reportService.getReport(id);
        return report.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 모든 Report 에 대한 Get 요청 - Get All Reports
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    // 그룹 채팅 ID(스터디 그룹의 FK를 받은 그룹 채팅의 PK인 챗 ID의 FK...) 에 대한 Get 요청 - Get Reports By GroupChatId
    @GetMapping("/groupChat/{groupChatId}")
    public ResponseEntity<List<Report>> getReportsByGroupChatId(@PathVariable Long groupChatId) {
        List<Report> reports = reportService.getReportsByGroupChatId(groupChatId);
        return ResponseEntity.ok(reports);
    }

    // 신고자 ID 에 대한 Get 요청 - Get Reports By ReporterId
    @GetMapping("/reporter/{reporterId}")
    public ResponseEntity<List<Report>> getReportsByReporterId(@PathVariable String reporterId) {
        List<Report> reports = reportService.getReportsByReporterId(reporterId);
        return ResponseEntity.ok(reports);
    }

    // 신고대상 ID 에 대한 Get 요청 - Get Reports By ReportedUserId
    @GetMapping("/reportedUser/{reportedUserId}")
    public ResponseEntity<List<Report>> getReportsByReportedUserId(@PathVariable String reportedUserId) {
        List<Report> reports = reportService.getReportsByReportedUserId(reportedUserId);
        return ResponseEntity.ok(reports);
    }

    // 처리 상태(BAN, WAITING, UNBAN) 에 대한 Get 요청 - Get Reports By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Report>> getReportsByStatus(@PathVariable ReportStatus status) {
        List<Report> reports = reportService.getReportsByStatus(status);
        return ResponseEntity.ok(reports);
    }

    // 생성 날짜(특정 날짜 이후) 에 대한 Get 요청 - Get Reports By CreatedAtAfter
    @GetMapping("/createdAtAfter")
    public ResponseEntity<List<Report>> getReportsByCreatedAtAfter(@RequestParam Date date) {
        List<Report> reports = reportService.getReportsByCreatedAtAfter(date);
        return ResponseEntity.ok(reports);
    }

    // 생성 날짜(특정 날짜 범위) 에 대한 Get 요청 - Get Reports By CreatedAtBetween
    @GetMapping("/createdAtBetween")
    public ResponseEntity<List<Report>> getReportsByCreatedAtBetween(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<Report> reports = reportService.getReportsByCreatedAtBetween(startDate, endDate);
        return ResponseEntity.ok(reports);
    }

    // 신고 ID 상태 변경를 위한 Put 요청 - Update Report Status
    @PutMapping("/{id}/status")
    public ResponseEntity<Report> updateReportStatus(@PathVariable Long id, @RequestParam ReportStatus status) {
        Report updatedReport = reportService.updateReportStatus(id, status);
        if (updatedReport != null) {
            return ResponseEntity.ok(updatedReport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
