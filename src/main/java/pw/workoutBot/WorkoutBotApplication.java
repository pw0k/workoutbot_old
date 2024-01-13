package pw.workoutBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//todo linter stopbugs
@SpringBootApplication
@EnableScheduling
public class WorkoutBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkoutBotApplication.class, args);
    }
}
