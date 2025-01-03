package org.kolis1on.codeseektest.controller;


import lombok.RequiredArgsConstructor;
import org.kolis1on.codeseektest.dto.response.TransferResponse;
import org.kolis1on.codeseektest.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<TransferResponse> transferPlayer(@RequestParam UUID playerUuid, @RequestParam UUID toTeamUuid) {
        TransferResponse response = transferService.transferPlayer(playerUuid, toTeamUuid).toDTO();
        return ResponseEntity.ok(response);
    }
}
