package ac.su.schedule_web_prj_be.controller;

import ac.su.schedule_web_prj_be.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

}
