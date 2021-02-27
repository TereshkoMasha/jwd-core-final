package com.epam.jwd.core_final.domain.factory.impl;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.domain.factory.EntityFactory;

import java.util.HashMap;
import java.util.Map;

public class SpaceshipFactory implements EntityFactory<Spaceship> {
    @Override
    public Spaceship create(Object... objects) {
        SpaceshipCriteria criteria = new SpaceshipCriteria();

        Map<Role, Short> map = new HashMap<>();
        map.put(Role.MISSION_SPECIALIST, Short.parseShort(objects[2].toString()));
        map.put(Role.FLIGHT_ENGINEER, Short.parseShort(objects[3].toString()));
        map.put(Role.PILOT, Short.parseShort(objects[4].toString()));
        map.put(Role.COMMANDER, Short.parseShort(objects[5].toString()));
        criteria.makeCriteria(map, objects[0], Long.parseLong(objects[1].toString()));

        return criteria.getResult();
    }
}
