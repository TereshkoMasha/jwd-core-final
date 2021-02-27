package com.epam.jwd.core_final.domain.factory.impl;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.factory.EntityFactory;

public class FlightMissionFactory implements EntityFactory<FlightMission> {

    @Override
    public FlightMission create(Object... objects) {
        FlightMissionCriteria criteria = new FlightMissionCriteria();
        criteria.makeCriteria(objects);
        return criteria.getResult();
    }
}
