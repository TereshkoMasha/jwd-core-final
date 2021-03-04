package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.HashMap;
import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

    private Map<Role, Short> crew;
    private Long flightDistance;
    private String name;

    public SpaceshipCriteria(Long id, String name) {
        super(id, name);
    }


    @Override
    void criteria(Object... objects) {
        for (Object criteria :
                objects) {
            switch (criteria.getClass().getSimpleName()) {
                case "Long": {
                    this.flightDistance = (Long) criteria;
                    break;
                }
                case "HashMap": {
                    this.crew = (Map<Role, Short>) criteria;
                    break;
                }
                case "String": {
                    this.name = (String) criteria;
                }
            }
        }
    }

    public Spaceship getResult() {
        return new Spaceship(name, flightDistance, crew);
    }
}
