package org.kolis1on.codeseektest;

import org.kolis1on.codeseektest.entity.Player;
import org.kolis1on.codeseektest.entity.Team;
import org.kolis1on.codeseektest.repository.PlayerRepository;
import org.kolis1on.codeseektest.repository.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public DataInitializer(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if teams already exist to avoid duplication
        Optional<Team> existingTeam1 = teamRepository.findByName("Team A");
        Optional<Team> existingTeam2 = teamRepository.findByName("Team B");

        if (existingTeam1.isEmpty() && existingTeam2.isEmpty()) {
            Team team1 = Team.builder()
                    .name("Team A")
                    .accountBalance(100000.00)
                    .commissionPercentage(10.0)
                    .build();

            Team team2 = Team.builder()
                    .name("Team B")
                    .accountBalance(200000.00)
                    .commissionPercentage(12.0)
                    .build();

            teamRepository.saveAll(Arrays.asList(team1, team2));

            Player player1 = Player.builder()
                    .name("Player 1")
                    .age(25)
                    .experienceMonths(24)
                    .team(team1)
                    .build();

            Player player2 = Player.builder()
                    .name("Player 2")
                    .age(28)
                    .experienceMonths(30)
                    .team(team1)
                    .build();

            Player player3 = Player.builder()
                    .name("Player 3")
                    .age(23)
                    .experienceMonths(18)
                    .team(team2)
                    .build();

            Player player4 = Player.builder()
                    .name("Player 4")
                    .age(27)
                    .experienceMonths(27)
                    .team(team2)
                    .build();

            playerRepository.saveAll(Arrays.asList(player1, player2, player3, player4));
        }
    }
}
