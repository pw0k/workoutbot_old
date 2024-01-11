package pw.workoutBot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static pw.workoutBot.util.MessageUtil.createMessage;

@Component
public class StartCommand extends BotCommand {

    public StartCommand() {
        super(CommandName.START.getValue(), "");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        try {
            absSender.execute(createMessage(chat.getId().toString(), "Добро пожаловать!"));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
