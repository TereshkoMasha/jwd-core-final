package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private String missionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long distance;
    private Spaceship assignedSpaceShift;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;

    @Override
    void criteria(Object... objects) {
        for (Object criteria :
                objects) {
            switch (criteria.getClass().getSimpleName()) {
                case "String": {
                    this.missionName = (String) criteria;
                    break;
                }
                case "LocalDate": {
                    this.startDate = (LocalDate) criteria;
                    this.endDate = (LocalDate) criteria;
                    break;
                }
                case "Spaceship": {
                    this.assignedSpaceShift = (Spaceship) criteria;
                    this.distance = assignedSpaceShift.getFlightDistance();
                    break;
                }
                case "ArrayList": {
                    this.assignedCrew = (List<CrewMember>) criteria;
                    break;
                }
                case "MissionResult": {
                    this.missionResult = (MissionResult) criteria;
                }
            }
        }
    }

    public FlightMission getResult() {
        return new FlightMission(missionName, startDate, endDate, distance, assignedSpaceShift, assignedCrew, missionResult);
    }
}