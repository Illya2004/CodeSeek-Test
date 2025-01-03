package org.kolis1on.codeseektest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponse{

    private UUID uuid;
    private String name;

    private int age;

    private int experienceMonths;

    private TeamResponse team;
}
