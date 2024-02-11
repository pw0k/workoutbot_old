package pw.workoutBot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pw.workoutBot.config.BotProperties;
import pw.workoutBot.model.Video;
import pw.workoutBot.model.WorkoutUser;

import java.util.*;

@Service
@Slf4j
public class TelegramBot extends TelegramLongPollingCommandBot {

    private final BotProperties botProperties;
    private final List<BotCommand> botCommands;
    private final TelegramDbService telegramDbService;
    private final Set<Long> chatIds;

    public TelegramBot(BotProperties botProperties,
                       List<BotCommand> botCommands,
                       TelegramDbService telegramDbService) {
        super();
        this.botCommands = botCommands;
        this.telegramDbService = telegramDbService;
        this.botProperties = botProperties;
        if (botCommands.isEmpty()) {
            log.error("Bot commands are missing!");
        }
        this.chatIds = Set.of(botProperties.getGeneralChat(), botProperties.getTestChat());
        botCommands.forEach(this::register);
    }

    // todo like for video, to check for anomalous too
    // (after api updated to tg api 7.0, for now 6.8)
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
                log.info("wow, new video from user {}", user.getUserName());
                return;
            }
            //todo delete after debugging
            log.warn("user {} {}", update.getMessage().getFrom().getUserName()
                    , update.getMessage().getFrom().getUserName() + update.getMessage().toString());
        }
    }

    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error occurred: {}", e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return botProperties.getBotName();
    }

    @Override
    public String getBotToken() {
        return botProperties.getToken();
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
