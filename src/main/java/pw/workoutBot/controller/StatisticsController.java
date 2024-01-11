package pw.workoutBot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pw.workoutBot.service.StatisticsService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/monthly")
    public void getMonthlyStatistics() {
        statisticsService.calculateMonthlyStatistics();
    }
}
