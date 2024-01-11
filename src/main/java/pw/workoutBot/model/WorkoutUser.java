package pw.workoutBot.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "workout_user")
public class WorkoutUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String telegramId;
    @Column(nullable = false)
    private String userName;
    private String firstName;

}
