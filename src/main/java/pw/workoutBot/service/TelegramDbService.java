package pw.workoutBot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pw.workoutBot.model.Video;
import pw.workoutBot.model.VideoRepository;
import pw.workoutBot.model.WorkoutUser;
import pw.workoutBot.model.WorkoutUserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TelegramDbService {

    private final VideoRepository videoRepository;
    private final WorkoutUserRepository workoutUserRepository;

    //todo indexing for date ?? and analyze sql ...
    //todo partitioning per month ?) ...
    @Transactional
    public void saveVideo(WorkoutUser user, Video video) {
        Optional<WorkoutUser> existingUser = workoutUserRepository.findByTelegramId(user.getTelegramId());
        if (existingUser.isEmpty()) {
            existingUser = Optional.of(workoutUserRepository.save(user));
        }
        video.setWorkoutUser(existingUser.get());
        video.setCreatedAt(LocalDateTime.now());
        videoRepository.save(video);
    }
}
