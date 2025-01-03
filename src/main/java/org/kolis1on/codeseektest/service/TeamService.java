package org.kolis1on.codeseektest.service;



import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.kolis1on.codeseektest.dto.request.TeamRequest;
import org.kolis1on.codeseektest.dto.response.TeamResponse;
import org.kolis1on.codeseektest.entity.Team;
import org.kolis1on.codeseektest.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public List<TeamResponse> getAllTeams() {
        return teamRepository.findAll().stream().map(Team::toDTO).toList();
    }

    public Team getTeamByUUID(UUID uuid) {
        return teamRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + uuid));
    }

    public TeamResponse createTeam(TeamRequest team) {
        return teamRepository.save(team.toEntity())
                .toDTO();
    }

    public TeamResponse updateTeam(UUID uuid, TeamRequest updatedTeam) {
        Team existingTeam = getTeamByUUID(uuid);
        existingTeam.setName(updatedTeam.getName());
        existingTeam.setAccountBalance(updatedTeam.getAccountBalance());
        existingTeam.setCommissionPercentage(updatedTeam.getCommissionPercentage());
        return teamRepository.save(existingTeam).toDTO();
    }

    public void deleteTeam(UUID uuid) {
        Team team = getTeamByUUID(uuid);
        teamRepository.delete(team);
    }
}
