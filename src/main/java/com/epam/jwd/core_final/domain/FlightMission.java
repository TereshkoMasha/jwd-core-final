package com.epam.jwd.core_final.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 * from {@link Planet}
 * to {@link Planet}
 */
public class FlightMission extends AbstractBaseEntity {
    // todo
    @Getter
    private final LocalDateTime startDate;
    @Getter
    private final LocalDateTime endDate;
    @Getter
    private final Long distance;
    @Getter
    @Setter
    private Spaceship assignedSpaceShift;
    @Getter
    @Setter
    private List<CrewMember> assignedCrew;
    @Getter
    @Setter
    private MissionResult missionResult;
    private Planet departurePoint;
    private Planet arrivalPoint;

    public FlightMission(String name, LocalDateTime startDate, LocalDateTime endDate, Long distance, Spaceship assignedSpaceShift, List<CrewMember> assignedCrew, MissionResult missionResult) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.assignedSpaceShift = assignedSpaceShift;
        this.assignedCrew = assignedCrew;
        this.missionResult = missionResult;
    }

}
