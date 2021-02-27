package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;
import lombok.Getter;

public enum Role implements BaseEntity {
    MISSION_SPECIALIST(1L),
    FLIGHT_ENGINEER(2L),
    PILOT(3L),
    COMMANDER(4L);

    @Getter
    private final Long id;

    Role(Long id) {
        this.id = id;
    }


    /**
     * todo via java.lang.enum methods!
     */
    @Override
    public String getName() {

        Role[] values = values();
        if (this.id == 1L) return values[0].name();
        if (this.id == 2L) return values[1].name();
        if (this.id == 3L) return values[2].name();
        if (this.id == 4L) return values[3].name();

        return null;
    }

    /**
     * todo via java.lang.enum methods!
     *
     * @throws UnknownEntityException if such id does not exist
     */
    public static Role resolveRoleById(int id) throws UnknownEntityException {
        Role[] values = values();
        for (Role role :
                values) {
            if (role.getId() == id) return role;
        }
        throw new UnknownEntityException("Such id doesn't exist!");
    }
}
