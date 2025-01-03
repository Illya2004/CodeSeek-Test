package org.kolis1on.codeseektest.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kolis1on.codeseektest.dto.response.TransferResponse;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "from_team_id")
    private Team fromTeam;

    @ManyToOne
    @JoinColumn(name = "to_team_id")
    private Team toTeam;

    private double transferAmount;

    private double commission;

    private double totalAmount;

    private LocalDateTime transferDate;

    public TransferResponse toDTO(){
        return TransferResponse.builder()
                .uuid(this.uuid)
                .player(this.player.toDTO())
                .fromTeam(this.fromTeam.toDTO())
                .toTeam(this.toTeam.toDTO())
                .commission(this.commission)
                .transferAmount(this.transferAmount)
                .totalAmount(this.totalAmount)
                .transferDate(this.transferDate)
                .build();
    }
}
