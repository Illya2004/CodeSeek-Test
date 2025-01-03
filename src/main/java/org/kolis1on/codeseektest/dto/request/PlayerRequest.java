package org.kolis1on.codeseektest.dto.request;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kolis1on.codeseektest.entity.Player;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 40, message = "Age must be at most 40")
    private int age;

    @Min(value = 1, message = "Experience must be at least 1 month")
    private int experienceMonths;

    public Player toEntity(){
        return Player.builder()
                .name(this.name)
                .age(this.age)
                .experienceMonths(this.experienceMonths)
                .build();

    }
}
