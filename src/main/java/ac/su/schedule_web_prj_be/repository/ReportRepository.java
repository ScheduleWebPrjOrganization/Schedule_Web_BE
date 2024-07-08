package ac.su.schedule_web_prj_be.repository;

import ac.su.schedule_web_prj_be.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    // 챗 번호로 검색
    List<Report> findByGroupChatId(Long groupChatId);
    // 신고자로 검색
    List<Report> findByReporter_id(String reporterId);
    // 신고대상으로 검색
    List<Report> findByReported_user_id(String reportedUserId);


    // 처리 상태로 검색
    List<Report> findByStatus(String status);

    // 최신순 조회
    List<Report> findAllByOrderByCreated_atDesc();

    // 날짜로 조회 (특정 날짜 이후)
    List<Report> findByCreated_atAfter(Date date);

    // 날짜로 조회 (특정 날짜 범위)
    List<Report> findByCreated_atBetween(Date startDate, Date endDate);
}