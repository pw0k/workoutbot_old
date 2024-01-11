package pw.workoutBot.scheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pw.workoutBot.service.StatisticsService;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class WeeklyStatsScheduler {

    private final StatisticsService statisticsService;

    @Scheduled(cron = "0 0 13 * * SUN")
    public void generateAndSendWeeklyStats() {
        statisticsService.calculateWeeklyStatistics();
    }
}
