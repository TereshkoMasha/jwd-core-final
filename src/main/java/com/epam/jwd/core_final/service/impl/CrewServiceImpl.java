package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.util.CrewMemberDeserializer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {
    private final Logger logger = Logger.getLogger(CrewServiceImpl.class);
    public static final CrewServiceImpl instance = new CrewServiceImpl();

    private List<CrewMemberCriteria> infFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(CrewMemberCriteria.class, new CrewMemberDeserializer());
        mapper.registerModule(module);
        return mapper.readValue(new File("src/main/resources/output/crew.json"),
                new TypeReference<List<CrewMemberCriteria>>() {
                });
    }

    @Override
    public List<CrewMember> findAllCrewMembers() throws IOException {
        //читаю из джейсона всех крю, если какого-то нет в списке, то добавляю его в кэш, возвращаю кэщ
        List<CrewMemberCriteria> crewMemberCriteria = infFromFile();
        for (CrewMemberCriteria criteria :
                crewMemberCriteria) {
            if (NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                    .stream()
                    .noneMatch(crewMember -> crewMember.getId().equals(criteria.getId())
                            && crewMember.getName().equals(criteria.getName())
                            && crewMember.getRole() == criteria.getRole()
                            && crewMember.getRank() == criteria.getRank()
                            && criteria.getIsReadyForNextMissions())) {
                NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class).add(new CrewMemberFactory().create(criteria.getRole(),
                        criteria.getRank(), criteria.getName()));
            }
        }
        return (List<CrewMember>) NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {

        return NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(CrewMember::getIsReadyForNextMissions)
                .filter((crewMember) -> {
                    if (criteria.getName() != null) {
                        return crewMember.getName().equals(criteria.getName());
                    }
                    return true;
                })
                .filter((crewMember) -> {
                    if ((criteria).getId() != null) {
                        return crewMember.getId().equals(criteria.getId());
                    }
                    return true;
                })
                .filter((crewMember) -> {
                    if (((CrewMemberCriteria) criteria).getRank() != null) {
                        return crewMember.getRank() == ((CrewMemberCriteria) criteria).getRank();
                    }
                    return true;
                })
                .filter((crewMember -> {
                    if (((CrewMemberCriteria) criteria).getRole() != null) {
                        return crewMember.getRole() == ((CrewMemberCriteria) criteria).getRole();
                    }
                    return true;
                }))
                .collect(Collectors.toList());
    }


    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        return NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(CrewMember::getIsReadyForNextMissions)
                .filter((crewMember) -> {
                    if (criteria.getName() != null) {
                        return crewMember.getName().equals(criteria.getName());
                    }
                    return true;
                })
                .filter((crewMember) -> {
                    if ((criteria).getId() != null) {
                        return crewMember.getId().equals(criteria.getId());
                    }
                    return true;
                })
                .filter((crewMember) -> {
                    if (((CrewMemberCriteria) criteria).getRank() != null) {
                        return crewMember.getRank() == ((CrewMemberCriteria) criteria).getRank();
                    }
                    return true;
                })

                .filter((crewMember -> {
                    if (((CrewMemberCriteria) criteria).getRole() != null) {
                        return crewMember.getRole() == ((CrewMemberCriteria) criteria).getRole();
                    }
                    return true;
                }))
                .findAny();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        List<CrewMember> crewMemberList = (List<CrewMember>) NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
        Criteria.CriteriaBuilder<CrewMember> criteria = new CrewMemberCriteria.CrewMemberCriteriaBuilder().setRank(crewMember.getRank())
                .setRole(crewMember.getRole())
                .setIsReadyForNextMissions(crewMember.getIsReadyForNextMissions())
                .setId(crewMember.getId()).setName(crewMember.getName());
        if (findCrewMemberByCriteria(criteria.createCriteria()).isPresent()) {
            return crewMember;
        }
        throw new UnknownEntityException("");
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws IOException {
        List<CrewMemberCriteria> criteria = infFromFile();
        long count = criteria
                .stream()
                .filter(crewMemberCriteria -> crewMember.getName().equals(crewMemberCriteria.getName()))
                .count();
        if (count == 0) {
            return crewMember;
        } else {
            logger.error(new UnknownEntityException("Crew with the same name already exists"));
            throw new UnknownEntityException("Crew with the same name already exists");
        }
    }
}
