package ac.su.schedule_web_prj_be.service;

import ac.su.schedule_web_prj_be.domain.Report;
import ac.su.schedule_web_prj_be.domain.ReportStatus;
import ac.su.schedule_web_prj_be.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {
    // Repo 주입
    private final ReportRepository reportRepository;

    // Report 생성 - 신고자, 신고대상, 챗 번호, 내용
    public Report createReport(Report report) {
        report.setCreatedAt(new Date());
        report.setStatus(ReportStatus.WAITING.toString());
        return reportRepository.save(report);
    }

    // 모든 Report 조회
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // Report ID로 조회
    public Optional<Report> getReport(Long id) {
        return reportRepository.findById(id);
    }

    // 챗 번호로 조회
    public List<Report> getReportsByGroupChatId(Long groupChatId) {
        return reportRepository.findByGroupChatId(groupChatId);
    }

    // 신고자로 조회
    public List<Report> getReportsByReporterId(String reporterId) {
        return reportRepository.findByReporterId(reporterId);
    }

    // 신고대상으로 조회
    public List<Report> getReportsByReportedUserId(String reportedUserId) {
        return reportRepository.findByReportedUserId(reportedUserId);
    }

    // 처리 상태로 조회
    public List<Report> getReportsByStatus(ReportStatus status) {
        return reportRepository.findByStatus(status);
    }

    // 날짜로 조회 (특정 날짜 이후)
    public List<Report> getReportsByCreatedAtAfter(Date date) {
        return reportRepository.findByCreatedAtAfter(date);
    }

    // 날짜로 조회 (특정 날짜 범위)
    public List<Report> getReportsByCreatedAtBetween(Date startDate, Date endDate) {
        return reportRepository.findByCreatedAtBetween(startDate, endDate);
    }

    // 최신순 조회
    public List<Report> getAllReportsOrderedByCreatedAtDesc() {
        return reportRepository.findAllByOrderByCreatedAtDesc();
    }

    // Report 상태 변경
    public Report updateReportStatus(Long id, ReportStatus status) {
        Optional<Report> reportOpt = reportRepository.findById(id);
        if (reportOpt.isPresent()) {
            Report report = reportOpt.get();
            report.setStatus(status.toString());
            return reportRepository.save(report);
        }
        return null;
    }
}
