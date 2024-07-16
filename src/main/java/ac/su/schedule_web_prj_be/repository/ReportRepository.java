package ac.su.schedule_web_prj_be.repository;

import ac.su.schedule_web_prj_be.domain.Report;
import ac.su.schedule_web_prj_be.domain.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    // 그룹 내부의 채팅 ID로 검색
    List<Report> findByGroupChatId(Long groupChatId);

    // 신고자로 검색
    List<Report> findByReporterId(String reporterId);

    // 신고대상으로 검색
    List<Report> findByReportedUserId(String reportedUserId);

    // 처리 상태로 검색
    List<Report> findByStatus(ReportStatus status);

    // 최신순 조회
    List<Report> findAllByOrderByCreatedAtDesc();

    // 날짜로 조회 (특정 날짜 이후)
    List<Report> findByCreatedAtAfter(Date date);

    // 날짜로 조회 (특정 날짜 범위)
    List<Report> findByCreatedAtBetween(Date startDate, Date endDate);

}