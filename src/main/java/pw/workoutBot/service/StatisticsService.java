package pw.workoutBot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pw.workoutBot.config.BotProperties;
import pw.workoutBot.dto.VideoStatistic;
import pw.workoutBot.model.VideoRepository;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final VideoRepository videoRepository;
    private final TelegramBot telegramBot;
    private final BotProperties botProperties;

    public List<VideoStatistic> calculateStatistics(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return videoRepository.findVideoCountByUserBetweenDates(startDateTime, endDateTime)
                .stream()
                .map(videoStat -> new VideoStatistic(videoStat.getUsername(), videoStat.getVideoCount()))
                .collect(Collectors.toList());
    }

    //todo: need to make the messages more readable.
    //todo: and add tests for logic later ...
    public void calculateWeeklyStatistics(Long chatId) {
        LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDateTime endOfWeek = LocalDateTime.of(nextSunday, LocalTime.of(13, 0));
        LocalDateTime startOfWeek = endOfWeek.minusDays(7);
        List<VideoStatistic> statistics = calculateStatistics(startOfWeek, endOfWeek);
        telegramBot.sendMessage(chatId, "Stat for Week:\n" + convertMsg(statistics));
    }

    public void calculateMonthlyStatistics(Long chatId) {
        LocalDateTime startOfMonth = YearMonth.now().atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = YearMonth.now().atEndOfMonth().atTime(23, 59, 59);
        List<VideoStatistic> statistics = calculateStatistics(startOfMonth, endOfMonth);
        telegramBot.sendMessage(chatId, "Stat for Month:\n" + convertMsg(statistics));
    }

    public void calculateWeeklyStatisticsForTestChat() {
        calculateMonthlyStatistics(botProperties.getTestChat());
    }

    private String convertMsg(List<VideoStatistic> statistics) {
        StringBuilder sb = new StringBuilder();
        for (var stat : statistics) {
            sb.append(stat.getUsername())
                    .append(" -> ")
                    .append(stat.getVideoCount())
                    .append(" training sessions")
                    .append("\n");
        }
        return sb.toString();
    }

}