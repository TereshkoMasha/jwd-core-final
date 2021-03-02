package com.epam.jwd.core_final.domain.factory.impl;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.factory.EntityFactory;

public class PlanetFactory implements EntityFactory<Planet> {
    @Override
    public Planet create(Object... objects) {
        String name = null;
        int x = Integer.parseInt(objects[0].toString());
        int y = Integer.parseInt(objects[1].toString());
        for (Object object :
                objects) {
            if ("String".equals(object.getClass().getSimpleName())) {
                name = object.toString();
            }
        }
        return new Planet(name, new Planet.Point(x, y));
    }
}
