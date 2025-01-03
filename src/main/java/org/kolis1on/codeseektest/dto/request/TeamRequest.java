package org.kolis1on.codeseektest.dto.request;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kolis1on.codeseektest.entity.Team;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequest {

    @NotBlank(message = "Team name cannot be blank")
    private String name;

    @DecimalMin(value = "0.0", inclusive = true, message = "Account balance must be non-negative")
    private double accountBalance;

    @DecimalMin(value = "0.0", inclusive = true, message = "Commission percentage must be at least 0%")
    @DecimalMax(value = "10.0", inclusive = true, message = "Commission percentage must be at most 10%")
    private double commissionPercentage;

    public Team toEntity(){
        return Team.builder()
                .name(this.name)
                .accountBalance(this.accountBalance)
                .commissionPercentage(this.commissionPercentage)
                .build();
    }
}
