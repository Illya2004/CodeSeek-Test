package org.kolis1on.codeseektest.controller;

import lombok.RequiredArgsConstructor;
import org.kolis1on.codeseektest.dto.request.PlayerRequest;
import org.kolis1on.codeseektest.dto.response.PlayerResponse;
import org.kolis1on.codeseektest.entity.Player;
import org.kolis1on.codeseektest.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    @GetMapping
    public ResponseEntity<List<PlayerResponse>> getAllPlayers() {
        List<PlayerResponse> responses = playerService.getAllPlayers();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PlayerResponse> getPlayerByUUID(@PathVariable UUID uuid) {
        PlayerResponse response = playerService.getPlayerByUuid(uuid).toDTO();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PlayerResponse> createPlayer(@RequestBody PlayerRequest playerDTO, @RequestParam UUID teamUuid) {
        PlayerResponse response = playerService.createPlayer(playerDTO, teamUuid);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PlayerResponse> updatePlayer(@PathVariable UUID uuid, @RequestBody PlayerRequest updatedPlayer) {
        PlayerResponse response = playerService.updatePlayer(uuid, updatedPlayer);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletePlayer(@PathVariable UUID uuid) {
        playerService.deletePlayer(uuid);
        return ResponseEntity.noContent().build();
    }
}
