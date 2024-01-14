package pw.workoutBot.scheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pw.workoutBot.config.BotProperties;
import pw.workoutBot.service.StatisticsService;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class WeeklyStatsScheduler {

    private final StatisticsService statisticsService;
    private final BotProperties botProperties;

    @Scheduled(cron = "0 0 13 * * SUN")
    public void generateAndSendWeeklyStats() {
        statisticsService.calculateWeeklyStatistics(botProperties.getGeneralChat());
    }
}
