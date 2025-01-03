package org.kolis1on.codeseektest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kolis1on.codeseektest.dto.response.TeamResponse;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private String name;

    private double accountBalance;
    private double commissionPercentage;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players;

    public TeamResponse toDTO(){
        return TeamResponse.builder()
                .uuid(this.uuid)
                .name(this.name)
                .accountBalance(this.accountBalance)
                .commissionPercentage(this.commissionPercentage)
                .build();
    }


}
