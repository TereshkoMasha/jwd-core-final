package com.epam.jwd.core_final.domain.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.domain.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    @Override
    public CrewMember create(Object... objects) {
        Role role;
        switch (objects[0].toString()) {
            case "1": {
                role = Role.MISSION_SPECIALIST;
                break;
            }
            case "2": {
                role = Role.FLIGHT_ENGINEER;
                break;
            }
            case "3": {
                role = Role.PILOT;
                break;
            }
            case "4": {
                role = Role.COMMANDER;
                break;
            }
            default: {
                throw new UnknownEntityException("Unknown role");
            }
        }
        switch (objects[2].toString()) {
            case "1": {
                return new CrewMember(objects[1].toString(), role, Rank.TRAINEE);
            }
            case "2": {
                return new CrewMember(objects[1].toString(), role, Rank.SECOND_OFFICER);
            }
            case "3": {
                return new CrewMember(objects[1].toString(), role, Rank.FIRST_OFFICER);
            }
            case "4": {
                return new CrewMember(objects[1].toString(), role, Rank.CAPTAIN);
            }
            default: {
                throw new UnknownEntityException("Unknown rank");
            }
        }
    }
}
