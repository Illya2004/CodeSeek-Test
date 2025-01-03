package org.kolis1on.codeseektest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {

    private UUID uuid;

    private PlayerResponse player;

    private TeamResponse fromTeam;

    private TeamResponse toTeam;

    private double transferAmount;

    private double commission;

    private double totalAmount;

    private LocalDateTime transferDate;
}
