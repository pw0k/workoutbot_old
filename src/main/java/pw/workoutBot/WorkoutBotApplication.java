package pw.workoutBot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//todo linter stopbugs
@SpringBootApplication
@EnableScheduling
@Slf4j
public class WorkoutBotApplication {

	public static void main(String[] args) {
		logJVMProperties();
		SpringApplication.run(WorkoutBotApplication.class, args);
	}

	private static void logJVMProperties() {
		log.info("Max Memory: {}", Runtime.getRuntime().maxMemory());
		log.info("Total Memory: {}", Runtime.getRuntime().totalMemory());
		log.info("Free Memory: {}", Runtime.getRuntime().freeMemory());
		log.info("Available Processors: {}", Runtime.getRuntime().availableProcessors());
		log.info("Max Direct Memory Size: {}", System.getProperty("MaxDirectMemorySize"));
		log.info("Max Metaspace Size: {}", System.getProperty("MaxMetaspaceSize"));
		log.info("Reserved Code Cache Size: {}", System.getProperty("ReservedCodeCacheSize"));
		log.info("Thread Stack Size: {}", System.getProperty("ThreadStackSize"));
	}

}
