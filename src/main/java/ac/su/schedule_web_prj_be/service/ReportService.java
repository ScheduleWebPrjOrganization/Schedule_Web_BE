package ac.su.schedule_web_prj_be.service;

import ac.su.schedule_web_prj_be.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
}
