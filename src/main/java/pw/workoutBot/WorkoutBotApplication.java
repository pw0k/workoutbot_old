package pw.workoutBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// todo linter stopbugs
// todo add optimizer vlad mihaleca
@SpringBootApplication
@EnableScheduling
public class WorkoutBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkoutBotApplication.class, args);
    }
}
