package com.epam.jwd.core_final.domain;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */

@JsonDeserialize(as = CrewMember.class)
public class CrewMember extends AbstractBaseEntity {
    // todo
    @Getter
    private final Role role;
    @Getter
    private final Rank rank;
    @Setter
    @Getter
    private Boolean isReadyForNextMissions;

    public CrewMember(String name, Role role, Rank rank) {
        super(name);
        this.role = role;
        this.rank = rank;
        this.isReadyForNextMissions = true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewMember that = (CrewMember) o;
        return role == that.role && rank == that.rank && Objects.equals(isReadyForNextMissions, that.isReadyForNextMissions);
    }
    @Override
    public int hashCode() {
        return Objects.hash(role, rank, isReadyForNextMissions);
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "name=" + super.getName() +
                "role=" + role +
                ", rank=" + rank +
                ", isReadyForNextMissions=" + isReadyForNextMissions +
                '}';
    }
}
