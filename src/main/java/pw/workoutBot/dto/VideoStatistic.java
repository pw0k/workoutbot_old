package pw.workoutBot.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class VideoStatistic {
    private String username;
    private long videoCount;
}
