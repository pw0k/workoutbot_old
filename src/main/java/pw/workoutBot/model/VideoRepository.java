package pw.workoutBot.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pw.workoutBot.dto.VideoStatistic;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    @Query("""
            SELECT new pw.workoutBot.dto.VideoStatistic(wu.userName, COUNT(v))
            FROM Video v
            JOIN v.workoutUser wu
            WHERE v.createdAt BETWEEN :startDateTime AND :endDateTime
            GROUP BY wu.userName
            """)
    List<VideoStatistic> findVideoCountByUserBetweenDates(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
