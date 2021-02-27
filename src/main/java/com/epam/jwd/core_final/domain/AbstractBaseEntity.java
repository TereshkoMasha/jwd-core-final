package com.epam.jwd.core_final.domain;

import lombok.Getter;

/**
 * Expected fields:
 * <p>
 * id {@link Long} - entity id
 * name {@link String} - entity name
 */
public abstract class AbstractBaseEntity implements BaseEntity {
    @Getter private Long id;
    @Getter private final String name;

    AbstractBaseEntity(String name) {
        this.name = name;
    }
}
