package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {



    public void makeCriteria(Object... objects)
    {
        criteria(objects);
    }

    abstract void criteria(Object... objects);
}
