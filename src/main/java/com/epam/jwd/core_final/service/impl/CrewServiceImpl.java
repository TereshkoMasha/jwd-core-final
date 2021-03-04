package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.util.CrewMemberDeserializer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {
    private static final Logger logger = Logger.getLogger(CrewServiceImpl.class);

    public static final CrewServiceImpl instance = new CrewServiceImpl();

    @Override
    public List<CrewMember> findAllCrewMembers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(CrewMemberCriteria.class, new CrewMemberDeserializer());
        mapper.registerModule(module);
        List<CrewMemberCriteria> readValue = mapper.readValue(new File("src/main/resources/output/crew.json"),
                new TypeReference<List<CrewMemberCriteria>>() {});
        System.out.println(readValue);

        return (List<CrewMember>) NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {

        return NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter((crewMember) -> {
                    if(((CrewMemberCriteria) criteria).getRank() != null) {
                        return crewMember.getRank() == ((CrewMemberCriteria) criteria).getRank();
                    }
                    return true;
                })
                .filter((crewMember -> {
                    if(((CrewMemberCriteria) criteria).getRole() != null) {
                       return crewMember.getRole() == ((CrewMemberCriteria) criteria).getRole();
                    }
                    return true;
                }))
                .filter(CrewMember::getIsReadyForNextMissions)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        return NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(CrewMember::getIsReadyForNextMissions)
                .filter((crewMember) -> {
                    if(((CrewMemberCriteria) criteria).getRank() != null) {
                        return crewMember.getRank() == ((CrewMemberCriteria) criteria).getRank();
                    }
                    return true;
                })
                .filter((crewMember -> {
                    if(((CrewMemberCriteria) criteria).getRole() != null) {
                        return crewMember.getRole() == ((CrewMemberCriteria) criteria).getRole();
                    }
                    return true;
                }))
                .reduce(((crewMember, crewMember2) -> crewMember));
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        return crewMember;
    }

//    @Override
//    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException { //API!!!!!
//        if (!crewMember.getIsReadyForNextMissions()) {
//            throw new UnknownEntityException(crewMember.toString() + " cannot be assigned to mission");
//        } else {
//            for (FlightMission mission :
//                    NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class)) {
//                for (Map.Entry<Role, Short> entry :
//                        mission.getAssignedSpaceShift().getCrew().entrySet()) {
//                    if (crewMember.getRole() == entry.getKey() && crewMember.getIsReadyForNextMissions()) {
//                        crewMember.setIsReadyForNextMissions(false);
//                        mission.getAssignedCrew().add(crewMember);
//                    }
//                }
//            }
//        }
//
//    }


    @Override
    public CrewMember createCrewMember(CrewMember crewMember) {
        for (CrewMember crew:
             NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class)) {
            if(crew.getName().equals(crewMember.getName())){
                throw new UnknownEntityException("Crew with the same name already exists");
            }
        }
       return crewMember;
    }
}
