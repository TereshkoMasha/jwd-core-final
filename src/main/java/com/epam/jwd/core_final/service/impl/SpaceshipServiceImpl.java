package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.SpaceshipService;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {
    @Getter
    private static final SpaceshipServiceImpl instance = new SpaceshipServiceImpl();

    @Override
    public List<Spaceship> findAllSpaceships() {
        return (List<Spaceship>) NasaContext.getInstance().retrieveBaseEntityList(Spaceship.class);

    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        return NasaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> {
                    if (criteria.getId() != null) {
                        return spaceship.getId().equals(criteria.getId());
                    }
                    return true;
                })
                .filter(spaceship -> {
                    if (criteria.getName() != null) {
                        return spaceship.getName().equals(criteria.getName());
                    }
                    return true;
                })
                .filter(spaceship -> {
                    if (((SpaceshipCriteria) criteria).getFlightDistance() != null) {
                        return spaceship.getFlightDistance().equals(((SpaceshipCriteria) criteria).getFlightDistance());
                    }
                    return true;
                })
                .collect(Collectors.toList());

    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return NasaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> {
                    if (criteria.getId() != null) {
                        return spaceship.getId().equals(criteria.getId());
                    }
                    return true;
                })
                .filter(spaceship -> {
                    if (criteria.getName() != null) {
                        return spaceship.getName().equals(criteria.getName());
                    }
                    return true;
                })
                .filter(spaceship -> {
                    if (((SpaceshipCriteria) criteria).getFlightDistance() != null) {
                        return spaceship.getFlightDistance().equals(((SpaceshipCriteria) criteria).getFlightDistance());
                    }
                    return true;
                })
                .findAny();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        NasaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship1 -> spaceship1.getName() == spaceship.getName())
                .findAny().ifPresent(spaceship1 -> spaceship1.setIsReadyForNextMissions(spaceship.getIsReadyForNextMissions()));
        return spaceship;
    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        if (NasaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .noneMatch(spaceship1 -> spaceship.getName().equals(spaceship1.getName()))) {
            return spaceship;
        }
        throw new UnknownEntityException("Spaceship with the same name already exists");

    }
}
