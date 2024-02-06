package pw.workoutBot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pw.workoutBot.model.AbstractPostgresContainer;

//using AbstractPostgresContainer bcs problem with init context before @Testcontainers up
// -> so liquibase can't connect and fall
@SpringBootTest(classes = WorkoutBotApplication.class)
//class WorkoutBotApplicationTests extends AbstractPostgresContainer {
class WorkoutBotApplicationTests extends AbstractPostgresContainer{
	@Test
	void contextLoads() {
	}

}
