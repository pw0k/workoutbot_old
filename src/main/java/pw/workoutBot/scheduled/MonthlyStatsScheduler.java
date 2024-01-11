package pw.workoutBot.scheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pw.workoutBot.service.StatisticsService;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class MonthlyStatsScheduler {

    private final StatisticsService statisticsService;

    @Scheduled(cron = "0 0 13 28-31 * ?")
    public void generateAndSendMonthlyStats() {
        statisticsService.calculateMonthlyStatistics();
    }
}
