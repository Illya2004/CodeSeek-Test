package org.kolis1on.codeseektest.repository;

import org.kolis1on.codeseektest.entity.Player;
import org.kolis1on.codeseektest.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    Optional<Team> findByUuid(UUID uuid);

    Optional<Team> findByName(String teamA);
}
