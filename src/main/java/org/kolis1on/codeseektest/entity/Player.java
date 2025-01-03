package org.kolis1on.codeseektest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.kolis1on.codeseektest.dto.response.PlayerResponse;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private String name;

    private int age;

    private int experienceMonths;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;


    public PlayerResponse toDTO(){
        return PlayerResponse.builder()
                .uuid(this.uuid)
                .name(this.name)
                .age(this.age)
                .experienceMonths(this.experienceMonths)
                .team(this.team.toDTO())
                .build();
    }
}
