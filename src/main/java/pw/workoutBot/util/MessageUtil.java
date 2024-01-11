package pw.workoutBot.util;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MessageUtil {
    public static SendMessage createMessage(String chatId, String text) {
        return SendMessage.builder()
                .chatId(chatId)
                .text("_" + text + "_")
                .parseMode("Markdown")
                .disableWebPagePreview(true)
                .build();
    }
}
