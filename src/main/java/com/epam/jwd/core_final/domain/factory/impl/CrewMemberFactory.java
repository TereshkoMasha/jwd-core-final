package com.epam.jwd.core_final.domain.factory.impl;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.factory.EntityFactory;
import com.epam.jwd.core_final.exception.UnknownEntityException;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    @Override
    public CrewMember create(Object... objects) {
        CrewMemberCriteria.CrewMemberCriteriaBuilder criteria = new CrewMemberCriteria.CrewMemberCriteriaBuilder();
        Role role;
        Rank rank;
        String name;
        for (Object obj :
                objects) {
            if (obj.getClass() == Role.class) {
                switch (obj.toString()) {
                    case "1": {
                        criteria.setRole(Role.MISSION_SPECIALIST);
                        break;
                    }
                    case "2": {
                        criteria.setRole(Role.FLIGHT_ENGINEER);
                        break;
                    }
                    case "3": {
                        criteria.setRole(Role.PILOT);
                        break;
                    }
                    case "4": {
                        criteria.setRole(Role.COMMANDER);
                        break;
                    }
                    default: {
                        throw new UnknownEntityException("Unknown role", objects);
                    }
                }
            } else if (obj.getClass() == Rank.class) {
                switch (obj.toString()) {
                    case "1": {
                        criteria.setRank(Rank.TRAINEE);
                        break;
                    }
                    case "2": {
                        criteria.setRank(Rank.SECOND_OFFICER);
                        break;
                    }
                    case "3": {
                        criteria.setRank(Rank.FIRST_OFFICER);
                        break;
                    }
                    case "4": {
                        criteria.setRank(Rank.CAPTAIN);
                        break;
                    }
                    default: {
                        throw new UnknownEntityException("Unknown rank", objects);
                    }
                }
            } else if (obj.getClass() == String.class) {
                criteria.setName(obj.toString());
            }
        }
        CrewMemberCriteria criteria1 = criteria.build();
        return new CrewMember(criteria.getName(),criteria1.getRole(),criteria1.getRank());
    }
}