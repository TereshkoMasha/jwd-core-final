package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {

    @Getter
    private final LocalDateTime startDate;
    @Getter
    private final LocalDateTime endDate;
    @Getter
    private final Long distance;
    @Getter
    private final Spaceship assignedSpaceShift;
    @Getter
    private final List<CrewMember> assignedCrew;
    @Getter
    private final MissionResult missionResult;
    @Getter
    private Planet departurePoint;
    @Getter
    private Planet arrivalPoint;

    public FlightMissionCriteria(Long id, String name,
                                 LocalDateTime startDate, LocalDateTime endDate, Long distance, Spaceship assignedSpaceShift,
                                 List<CrewMember> assignedCrew,
                                 MissionResult missionResult, Planet departurePoint,
                                 Planet arrivalPoint) {
        super(id, name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.assignedSpaceShift = assignedSpaceShift;
        this.assignedCrew = assignedCrew;
        this.missionResult = missionResult;
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
    }

    public static class FlightMissionCriteriaBuilder extends CriteriaBuilder<FlightMission> {

        private Long id;
        private String name;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Long distance;
        private Spaceship assignedSpaceShift;
        private List<CrewMember> assignedCrew;
        private MissionResult missionResult;
        private Planet arrivalPoint;
        private Planet departurePoint;

        public FlightMissionCriteriaBuilder setArrivalPoint(Planet arrivalPoint) {
            this.arrivalPoint = arrivalPoint;
            return this;
        }

        public FlightMissionCriteriaBuilder setDeparturePoint(Planet departurePoint) {
            this.departurePoint = departurePoint;
            return this;
        }

        public FlightMissionCriteriaBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public FlightMissionCriteriaBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public FlightMissionCriteriaBuilder setStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public FlightMissionCriteriaBuilder setEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public FlightMissionCriteriaBuilder setDistance(Long distance) {
            this.distance = distance;
            return this;
        }

        public FlightMissionCriteriaBuilder setAssignedSpaceShift(Spaceship assignedSpaceShift) {
            this.assignedSpaceShift = assignedSpaceShift;
            return this;
        }

        public FlightMissionCriteriaBuilder setAssignedCrew(List<CrewMember> assignedCrew) {
            this.assignedCrew = assignedCrew;
            return this;
        }

        public FlightMissionCriteriaBuilder setMissionResult(MissionResult missionResult) {
            this.missionResult = missionResult;
            return this;
        }

        public FlightMissionCriteria createFlightMissionCriteria() {
            return new FlightMissionCriteria(id, name, startDate, endDate, distance, assignedSpaceShift,
                    assignedCrew, missionResult, departurePoint, arrivalPoint);
        }
    }

    @Override
    void criteria(Object... objects) {

    }
}