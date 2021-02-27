package com.epam.jwd.core_final.domain.factory;

import com.epam.jwd.core_final.domain.BaseEntity;

public interface EntityFactory<T extends BaseEntity> {

    T create(Object... objects);
}
