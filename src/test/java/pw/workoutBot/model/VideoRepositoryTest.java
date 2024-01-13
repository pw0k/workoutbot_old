package pw.workoutBot.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import pw.workoutBot.dto.VideoStatistic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VideoRepositoryTest extends AbstractPostgresContainer{

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private WorkoutUserRepository workoutUserRepository;
    @MockBean
    private TelegramBotsApi mockTelegramBotsApi;

    @Test
    void test() {
        System.out.println("UniqIdFindMe");
        System.out.println("JDBC URL: " + postgresContainer.getJdbcUrl());
        System.out.println("Username: " + postgresContainer.getUsername());
        System.out.println("Password: " + postgresContainer.getPassword());
        // your test code
    }

    @Test
    public void testFindVideoCountByUserBetweenDates() {
        // Given
        WorkoutUser user1 = createAndSaveUser("user1");
        WorkoutUser user2 = createAndSaveUser("user2");
        createVideoForUser(LocalDate.of(2012, 12, 21), user1);
        createVideoForUser(LocalDate.of(2012, 12, 23), user1);
        createVideoForUser(LocalDate.of(2012, 12, 22), user2);
        createVideoForUser(LocalDate.of(2013, 1, 1), user2);

        LocalDateTime startDate = LocalDateTime.of(LocalDate.of(2012, 12, 1), LocalTime.of(6, 29));
        LocalDateTime endDate = LocalDateTime.of(LocalDate.of(2012, 12, 30), LocalTime.of(0, 0));

        // When
        List<VideoStatistic> statistics = videoRepository.findVideoCountByUserBetweenDates(startDate, endDate);

        // Then
        assertEquals(2, statistics.size(), "There should be statistics for 2 users.");
        assertEquals(user1.getUserName(), statistics.get(0).getUsername(), "should be user1");
        assertEquals(2, statistics.get(0).getVideoCount(), "User1 should have 2 videos.");
        assertEquals(user2.getUserName(), statistics.get(1).getUsername(), "should be user2");
        assertEquals(1, statistics.get(1).getVideoCount(), "User1 should have 1 videos.");
    }

    private void createVideoForUser(LocalDate date, WorkoutUser user) {
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(23, 59));
        Video video = Video.builder()
                .fileUniqueId(UUID.randomUUID().toString())
                .workoutUser(user)
                .createdAt(dateTime)
                .build();
        videoRepository.save(video);
    }

    private WorkoutUser createAndSaveUser(String username) {
        WorkoutUser wk = WorkoutUser.builder()
                .userName(username)
                .telegramId(username)
                .build();
        return workoutUserRepository.save(wk);
    }
}