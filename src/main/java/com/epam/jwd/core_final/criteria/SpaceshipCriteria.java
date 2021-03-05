package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import lombok.Getter;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

    @Getter
    private final Map<Role, Short> crew;
    @Getter
    private final Long flightDistance;
    @Getter
    private final String name;


    public SpaceshipCriteria(Long id, String name, Map<Role, Short> crew, Long flightDistance, String name1) {
        super(id, name);
        this.crew = crew;
        this.flightDistance = flightDistance;
        this.name = name1;
    }

    public static class SpaceshipCriteriaBuilder extends CriteriaBuilder<Spaceship> {

        private Long id;
        private String name;
        private Map<Role, Short> crew;
        private Long flightDistance;
        private String name1;

        public SpaceshipCriteriaBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public SpaceshipCriteriaBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public SpaceshipCriteriaBuilder setCrew(Map<Role, Short> crew) {
            this.crew = crew;
            return this;
        }

        public SpaceshipCriteriaBuilder setFlightDistance(Long flightDistance) {
            this.flightDistance = flightDistance;
            return this;
        }

        public SpaceshipCriteriaBuilder setName1(String name1) {
            this.name1 = name1;
            return this;
        }

        public SpaceshipCriteria createSpaceshipCriteria() {
            return new SpaceshipCriteria(id, name, crew, flightDistance, name1);
        }
    }

    @Override
    void criteria(Object... objects) {

    }
}
