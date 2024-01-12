package pw.workoutBot;

import org.junit.jupiter.api.Test;
import pw.workoutBot.model.AbstractPostgresContainer;

//using AbstractPostgresContainer bcs problem with init context before @Testcontainers up
// -> so liquibase can't connect and fall
class WorkoutBotApplicationTests extends AbstractPostgresContainer {

	@Test
	void contextLoads() {
	}

}
