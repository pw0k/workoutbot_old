package pw.workoutBot.service;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pw.workoutBot.model.Video;
import pw.workoutBot.model.WorkoutUser;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class TelegramBot extends TelegramLongPollingCommandBot {

    @Value("${telegram.botName}")
    private String botName;
    @Value("${telegram.token}")
    private String token;
    private List<BotCommand> botCommands;
    private TelegramDbService telegramDbService;
    Set<Long> chatIds = Set.of(-4034880397L);

    public TelegramBot(List<BotCommand> botCommands, TelegramDbService telegramDbService) {
        super();
        this.botCommands = botCommands;
        if (botCommands.isEmpty()) log.error("Bot commands are missing!");
        botCommands.forEach(this::register);
        this.telegramDbService = telegramDbService;
    }

    @PostConstruct
    public void init() {
        botCommands.forEach(this::register);
    }

    //todo like for video, to check for anomalous too
    // (after api updated to tg api 7.0, for now 6.8)
    @SneakyThrows
    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()) {
            Long chatId = update.getMessage().getChatId();
            if (!chatIds.contains(chatId)) {
                log.warn("somebody trying to work with unregistered chat {}", chatId);
                return;
            }
            if (update.getMessage().hasVideo()) {
                //msg by bot
                if (update.getMessage().getFrom().getUserName() == null ) {
                    return;
                }
                WorkoutUser user = getUser(update);
                Video video = getVideo(update);
                telegramDbService.saveVideo(user, video);
            }
        }
    }

    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error occurred: {}", e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    private WorkoutUser getUser(Update update) {
        return WorkoutUser.builder()
                .telegramId(update.getMessage().getFrom().getId().toString())
                .userName(update.getMessage().getFrom().getUserName())
                .firstName(update.getMessage().getFrom().getFirstName())
                .build();
    }

    private Video getVideo(Update update) {
        return Video.builder()
                .fileUniqueId(update.getMessage().getVideo().getFileUniqueId())
                .build();
    }


}
