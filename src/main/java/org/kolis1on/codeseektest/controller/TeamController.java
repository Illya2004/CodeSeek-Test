package org.kolis1on.codeseektest.controller;

import lombok.RequiredArgsConstructor;
import org.kolis1on.codeseektest.dto.request.TeamRequest;
import org.kolis1on.codeseektest.dto.response.TeamResponse;
import org.kolis1on.codeseektest.entity.Team;
import org.kolis1on.codeseektest.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamResponse>> getAllTeams() {
        List<TeamResponse> responses = teamService.getAllTeams();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TeamResponse> getTeamByUUID(@PathVariable UUID uuid) {
        TeamResponse response = teamService.getTeamByUUID(uuid).toDTO();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@RequestBody TeamRequest team) {
        TeamResponse response = teamService.createTeam(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<TeamResponse> updateTeam(@PathVariable UUID uuid, @RequestBody TeamRequest updatedTeam) {
        TeamResponse response = teamService.updateTeam(uuid, updatedTeam);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteTeam(@PathVariable UUID uuid) {
        teamService.deleteTeam(uuid);
        return ResponseEntity.noContent().build();
    }
}
