package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.CrewService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {

    public static final CrewServiceImpl INSTANCE = new CrewServiceImpl();

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return (List<CrewMember>) NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {

        return NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter((crewMember -> crewMember.getName().equals(((CrewMemberCriteria) criteria).getName())))
                .filter((crewMember) -> crewMember.getRank() == ((CrewMemberCriteria) criteria).getRank())
                .filter((crewMember -> crewMember.getRole() == ((CrewMemberCriteria) criteria).getRole()))
                .collect(Collectors.toList());
    }


    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        return NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter((crewMember -> crewMember.getName().equals(((CrewMemberCriteria) criteria).getName())))
                .filter((crewMember) -> crewMember.getRank() == ((CrewMemberCriteria) criteria).getRank())
                .filter((crewMember -> crewMember.getRole() == ((CrewMemberCriteria) criteria).getRole()))
                .reduce(((crewMember, crewMember2) -> crewMember));
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        return crewMember;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
        if (!crewMember.getIsReadyForNextMissions()) {
            throw new UnknownEntityException("Crew member cannot be assigned on mission");
        } else {
            NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                    .stream()
                    .filter((s) -> s.getAssignedCrew().size() < s.getAssignedSpaceShift().getCrew().get(crewMember.getRole()));

        }
        crewMember.setIsReadyForNextMissions(true);
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException {
        if (NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                .stream().noneMatch(crewMemberList -> crewMember.getName().equals(crewMember.getName()))) {
            NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class).add(crewMember);
            return crewMember;
        } else {
            throw new UnknownEntityException("Name exception");
        }
    }
}
