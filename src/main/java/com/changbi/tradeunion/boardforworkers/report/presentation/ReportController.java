package com.changbi.tradeunion.boardforworkers.report.presentation;

import com.changbi.tradeunion.boardforworkers.report.application.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
}
