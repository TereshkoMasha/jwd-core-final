package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private String missionName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long distance;
    private Spaceship assignedSpaceShift;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;

    public FlightMissionCriteria(Long id, String name) {
        super(id, name);
    }

    @Override
    void criteria(Object... objects) {

    }
}