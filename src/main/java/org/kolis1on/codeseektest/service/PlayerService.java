package org.kolis1on.codeseektest.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.kolis1on.codeseektest.dto.request.PlayerRequest;
import org.kolis1on.codeseektest.dto.response.PlayerResponse;
import org.kolis1on.codeseektest.entity.Player;
import org.kolis1on.codeseektest.entity.Team;
import org.kolis1on.codeseektest.repository.PlayerRepository;
import org.kolis1on.codeseektest.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public List<PlayerResponse> getAllPlayers() {
        return playerRepository.findAll().stream().map(Player::toDTO).toList();
    }

    public Player getPlayerByUuid(UUID uuid) {
        return playerRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with id: " + uuid));
    }

    public PlayerResponse createPlayer(PlayerRequest playerDTO, @NotNull(message = "Team ID is required") UUID teamUUID) {
        Team team = teamRepository.findByUuid(teamUUID)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + teamUUID));
        Player player = playerDTO.toEntity();
        player.setTeam(team);
        return playerRepository.save(player).toDTO();
    }

    public PlayerResponse updatePlayer(UUID uuid, PlayerRequest updatedPlayer) {
        Player existingPlayer = getPlayerByUuid(uuid);
        existingPlayer.setName(updatedPlayer.getName());
        existingPlayer.setAge(updatedPlayer.getAge());
        existingPlayer.setExperienceMonths(updatedPlayer.getExperienceMonths());
        return playerRepository.save(existingPlayer).toDTO();
    }

    public void deletePlayer(UUID uuid) {
        Player player = getPlayerByUuid(uuid);
        playerRepository.delete(player);
    }
}
