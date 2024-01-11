package pw.workoutBot.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
enum CommandName {
    START("startCommand"),
    GET_STAT("getStatistics");

    private final String value;
}
