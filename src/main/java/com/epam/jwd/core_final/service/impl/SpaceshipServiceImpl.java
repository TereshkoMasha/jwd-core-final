package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.domain.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.List;
import java.util.Optional;

public class SpaceshipServiceImpl implements SpaceshipService {
    @Override
    public List<Spaceship> findAllSpaceships() {
        return (List<Spaceship>) NasaContext.getInstance().retrieveBaseEntityList(Spaceship.class);

    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        criteria.makeCriteria();
        return null;
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return Optional.empty();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        return null;
    }

//    @Override
//    public void assignSpaceshipOnMission(Spaceship spaceship) throws RuntimeException {
//        for (FlightMission mission :
//                NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class)) {
//            if (spaceship.getFlightDistance() >= mission.getDistance() && spaceship.getIsReadyForNextMissions()) {
//                mission.setAssignedSpaceShift(spaceship);
//                break;
//            }
//        }
//    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException { //////////////////
        return new SpaceshipFactory().create(spaceship);
    }
}
