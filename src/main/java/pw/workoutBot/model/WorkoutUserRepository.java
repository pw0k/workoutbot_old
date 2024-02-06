package pw.workoutBot.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutUserRepository extends JpaRepository<WorkoutUser, Long> {
    Optional<WorkoutUser> findByTelegramId(String telegramId);
}
