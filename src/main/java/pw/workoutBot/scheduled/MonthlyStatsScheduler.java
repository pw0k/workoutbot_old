package pw.workoutBot.scheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pw.workoutBot.config.BotProperties;
import pw.workoutBot.service.StatisticsService;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class MonthlyStatsScheduler {

    private final StatisticsService statisticsService;
    private final BotProperties botProperties;

    @Scheduled(cron = "0 0 13 28-31 * ?")
    public void generateAndSendMonthlyStats() {
        if (isLastDayOfMonth(LocalDate.now())) {
            statisticsService.calculateMonthlyStatistics(botProperties.getGeneralChat());
        }
    }

     boolean isLastDayOfMonth(LocalDate date) {
        LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        return date.getDayOfMonth() == lastDayOfMonth.getDayOfMonth();
    }
}
