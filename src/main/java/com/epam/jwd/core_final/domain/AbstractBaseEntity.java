package com.epam.jwd.core_final.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Expected fields:
 * <p>
 * id {@link Long} - entity id
 * name {@link String} - entity name
 */
public abstract class AbstractBaseEntity implements BaseEntity {
    private static long count = 0;
    @Setter
    @Getter
    private Long id;
    @Getter
    private final String name;

    AbstractBaseEntity(String name) {
        this.name = name;
        setId(++count);
    }
}
