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
    @Getter
    private Planet departurePoint;
    @Getter
    private Planet arrivalPoint;

    public FlightMission(String name, LocalDateTime startDate, LocalDateTime endDate
            , Long distance,MissionResult missionResult) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.missionResult = missionResult;
    }

    public FlightMission(String name, LocalDateTime startDate, LocalDateTime endDate, Long distance, MissionResult missionResult, Planet departurePoint, Planet arrivalPoint) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.missionResult = missionResult;
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
    }

    @Override
    public String toString() {
        return "FlightMission{" +
                "name=" + super.getName() +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", distance=" + distance +
                ", assignedSpaceShift=" + assignedSpaceShift +
                ", assignedCrew=" + assignedCrew +
                ", missionResult=" + missionResult +
                ", departurePoint=" + departurePoint +
                ", arrivalPoint=" + arrivalPoint +
                '}';
    }
}
