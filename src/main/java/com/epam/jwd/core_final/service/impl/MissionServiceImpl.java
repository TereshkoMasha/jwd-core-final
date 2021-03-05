package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.MissionService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {


    @Override
    public List<FlightMission> findAllMissions() {
        return (List<FlightMission>) NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class);
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        return NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(flightMission -> {
                    if (criteria.getName() != null) {
                        return flightMission.getName().equals(criteria.getName());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (criteria.getId() != null) {
                        return flightMission.getId().equals(criteria.getId());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (((FlightMissionCriteria) criteria).getDistance() != null) {
                        return flightMission.getDistance().equals(((FlightMissionCriteria) criteria).getDistance());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (((FlightMissionCriteria) criteria).getAssignedCrew() != null) {
                        return flightMission.getAssignedCrew().equals(((FlightMissionCriteria) criteria).getAssignedCrew());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (((FlightMissionCriteria) criteria).getAssignedSpaceShift() != null) {
                        return flightMission.getAssignedSpaceShift().equals(((FlightMissionCriteria) criteria).getAssignedSpaceShift());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (((FlightMissionCriteria) criteria).getStartDate() != null) {
                        return flightMission.getStartDate().equals(((FlightMissionCriteria) criteria).getStartDate());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (((FlightMissionCriteria) criteria).getEndDate() != null) {
                        return flightMission.getEndDate().equals(((FlightMissionCriteria) criteria).getEndDate());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (((FlightMissionCriteria) criteria).getArrivalPoint() != null) {
                        return flightMission.getArrivalPoint().equals(((FlightMissionCriteria) criteria).getArrivalPoint());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (((FlightMissionCriteria) criteria).getDeparturePoint() != null) {
                        return flightMission.getDeparturePoint().equals(((FlightMissionCriteria) criteria).getDeparturePoint());
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        return NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(flightMission -> {
                    if (criteria.getName() != null) {
                        return flightMission.getName().equals(criteria.getName());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (criteria.getId() != null) {
                        return flightMission.getId().equals(criteria.getId());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (((FlightMissionCriteria) criteria).getDistance() != null) {
                        return flightMission.getDistance().equals(((FlightMissionCriteria) criteria).getDistance());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (((FlightMissionCriteria) criteria).getAssignedCrew() != null) {
                        return flightMission.getAssignedCrew().equals(((FlightMissionCriteria) criteria).getAssignedCrew());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (((FlightMissionCriteria) criteria).getAssignedSpaceShift() != null) {
                        return flightMission.getAssignedSpaceShift().equals(((FlightMissionCriteria) criteria).getAssignedSpaceShift());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (((FlightMissionCriteria) criteria).getStartDate() != null) {
                        return flightMission.getStartDate().equals(((FlightMissionCriteria) criteria).getStartDate());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (((FlightMissionCriteria) criteria).getEndDate() != null) {
                        return flightMission.getEndDate().equals(((FlightMissionCriteria) criteria).getEndDate());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (((FlightMissionCriteria) criteria).getArrivalPoint() != null) {
                        return flightMission.getArrivalPoint().equals(((FlightMissionCriteria) criteria).getArrivalPoint());
                    }
                    return true;
                })
                .filter(flightMission -> {
                    if (((FlightMissionCriteria) criteria).getDeparturePoint() != null) {
                        return flightMission.getDeparturePoint().equals(((FlightMissionCriteria) criteria).getDeparturePoint());
                    }
                    return true;
                })
                .findAny();
    }

    @Override
    public FlightMission updateMissionDetails(FlightMission flightMission) {
        NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(flightMission1 -> flightMission1.getName().equals(flightMission.getName()))
                .findAny()
                .ifPresent(flightMission1 -> {
                    if (!flightMission1.getDistance().equals(flightMission.getDistance())) {
                        flightMission1.setMissionResult(flightMission.getMissionResult());
                    } else {
                        throw new UnknownEntityException(flightMission.getName() + " - the mission does not exist");
                    }
                });
        return flightMission;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        if (NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .noneMatch(mission -> mission.getName().equals(flightMission.getName()))) {
            return flightMission;
        }
        throw new UnknownEntityException("Mission with the same name already exists");
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws RuntimeException {
        NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(flightMission -> flightMission.getMissionResult() == MissionResult.PLANNED
                        && flightMission.getDistance() >= spaceship.getFlightDistance())
                .findAny()
                .ifPresent(flightMission -> flightMission.setAssignedSpaceShift(spaceship));
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
        NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(flightMission -> flightMission.getMissionResult()
                        == MissionResult.IN_PROGRESS && flightMission.getAssignedSpaceShift() != null)
                .findAny()
                .ifPresent(flightMission -> {
                    for (Map.Entry<Role, Short> map :
                            flightMission.getAssignedSpaceShift().getCrew().entrySet()) {
                        if (crewMember.getRole() == map.getKey() &&
                                (flightMission.getAssignedCrew()
                                        .stream()
                                        .filter(crewMember1 -> crewMember1.getRole() == crewMember.getRole())
                                        .count()) < map.getValue()) {
                            flightMission.getAssignedCrew().add(crewMember);
                        }
                    }
                });

    }
}
