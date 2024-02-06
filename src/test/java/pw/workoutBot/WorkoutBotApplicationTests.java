package pw.workoutBot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import pw.workoutBot.model.AbstractPostgresContainer;

//using AbstractPostgresContainer bcs problem with init context before @Testcontainers up
// -> so liquibase can't connect and fall
@SpringBootTest
class WorkoutBotApplicationTests extends AbstractPostgresContainer{

	//stub for Telegram API calls
	@MockBean
	private TelegramBotsApi telegramBotsApi;

	@Test
	void contextLoads() {
	}

}
