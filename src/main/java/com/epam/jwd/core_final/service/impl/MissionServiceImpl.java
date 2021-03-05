package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.MissionService;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {

    @Getter
    private static final MissionServiceImpl instance = new MissionServiceImpl();

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
        AtomicInteger inx = new AtomicInteger();
        NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(flightMission1 -> flightMission1.equals(flightMission))
                .filter(flightMission1 -> flightMission1.getDeparturePoint() != null && flightMission1.getArrivalPoint() != null)
                .findAny()
                .ifPresent(flightMission1 -> {
                    for (Map.Entry<Role, Short> entry :
                            flightMission1.getAssignedSpaceShift().getCrew().entrySet()) {
                        if (flightMission1.getAssignedCrew().stream().filter(crewMember -> crewMember.getRole() == entry.getKey())
                                .count() == entry.getValue()) {
                            inx.getAndIncrement();
                        }
                    }
                    if (inx.toString().equals("4")) {
                        flightMission1.setMissionResult(MissionResult.IN_PROGRESS);
                        System.out.println("New mission result" + MissionResult.IN_PROGRESS);
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
                .ifPresent(flightMission -> {
                    flightMission.setAssignedSpaceShift(spaceship);
                    System.out.println("Spaceship assign on mission " + flightMission.getName());
                });
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
        NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(flightMission -> flightMission.getMissionResult()
                        == MissionResult.PLANNED && flightMission.getAssignedSpaceShift() != null)
                .findAny()
                .ifPresent(flightMission -> {
                    for (Map.Entry<Role, Short> map :
                            flightMission.getAssignedSpaceShift().getCrew().entrySet()) {
                        if (crewMember.getRole() == map.getKey()) {
                            if (flightMission.getAssignedCrew().isEmpty()) {
                                flightMission.getAssignedCrew().add(crewMember);
                                System.out.println("Crew member " + crewMember.getName() + " assign on mission " + flightMission.getName());
                            } else {
                                if (flightMission.getAssignedCrew()
                                        .stream()
                                        .filter(crewMember1 -> crewMember1.getRole() == crewMember.getRole())
                                        .count() < map.getValue()) {
                                    flightMission.getAssignedCrew().add(crewMember);
                                    System.out.println("Crew member " + crewMember.getName() + " assign on mission " + flightMission.getName());
                                } else {
                                    System.out.println(crewMember.getRole().toString() + " in the right amount on the ship");
                                }

                            }
                        }
                    }
                });

    }

    @Override
    public void setPlanetsOnMission(Planet departure, Planet arrival) {
        NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(flightMission -> flightMission.getMissionResult() == MissionResult.PLANNED
                        && flightMission.getArrivalPoint() == null && flightMission.getDeparturePoint() == null
                        && (flightMission.getAssignedSpaceShift() != null) && (SpaceMapServiceImpl.getInstance().getDistanceBetweenPlanets(departure, arrival)
                        <= flightMission.getAssignedSpaceShift().getFlightDistance()))
                .findAny()
                .ifPresent(flightMission -> {
                    flightMission.setDeparturePoint(departure);
                    flightMission.setArrivalPoint(arrival);
                    System.out.println("Planets" + departure.getName()
                            + " " + arrival.getName() + " assign on mission " + flightMission.getName());
                });
    }

}
