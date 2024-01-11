package pw.workoutBot.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkoutUserRepository extends JpaRepository<WorkoutUser, Long> {
    Optional<WorkoutUser> findByTelegramId(String telegramId);
}
