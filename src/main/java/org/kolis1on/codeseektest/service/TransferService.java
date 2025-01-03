package org.kolis1on.codeseektest.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.kolis1on.codeseektest.entity.Player;
import org.kolis1on.codeseektest.entity.Team;
import org.kolis1on.codeseektest.entity.Transfer;
import org.kolis1on.codeseektest.repository.PlayerRepository;
import org.kolis1on.codeseektest.repository.TeamRepository;
import org.kolis1on.codeseektest.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final TransferRepository transferRepository;

    @Transactional
    public Transfer transferPlayer(UUID playerUUID, UUID toTeamUUID) {
        Player player = playerRepository.findByUuid(playerUUID)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with id: " + playerUUID));

        Team fromTeam = player.getTeam();
        Team toTeam = teamRepository.findByUuid(toTeamUUID)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + toTeamUUID));

        if (fromTeam.equals(toTeam)) {
            throw new IllegalArgumentException("Player is already in the selected team.");
        }

        double transferCost = player.getExperienceMonths() * 100000.0 / player.getAge();

        double commission = fromTeam.getCommissionPercentage() * transferCost / 100.0;

        double totalAmount = transferCost + commission;

        if (toTeam.getAccountBalance() < totalAmount) {
            throw new IllegalArgumentException("Insufficient balance in the buying team's account.");
        }

        toTeam.setAccountBalance(toTeam.getAccountBalance() - totalAmount);

        fromTeam.setAccountBalance(fromTeam.getAccountBalance() + totalAmount);

        player.setTeam(toTeam);
        playerRepository.save(player);

        Transfer transfer = Transfer.builder()
                .player(player)
                .fromTeam(fromTeam)
                .toTeam(toTeam)
                .transferAmount(transferCost)
                .commission(commission)
                .totalAmount(totalAmount)
                .transferDate(LocalDateTime.now())
                .build();

        return transferRepository.save(transfer);
    }
}
