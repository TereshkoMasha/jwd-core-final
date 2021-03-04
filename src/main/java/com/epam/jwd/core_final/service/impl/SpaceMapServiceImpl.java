package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.SpacemapService;

import java.util.ArrayList;
import java.util.Random;

public class SpaceMapServiceImpl implements SpacemapService {
    @Override
    public Planet getRandomPlanet() {
        Random rand = new Random();
        ArrayList<Planet> planets = (ArrayList<Planet>) NasaContext.getInstance().retrieveBaseEntityList(Planet.class);
        return planets.get(rand.nextInt(planets.size()));
    }

    @Override
    public double getDistanceBetweenPlanets(Planet first, Planet second) {
        return Math.sqrt((second.getPoint().getX() - first.getPoint().getY() )
                * (second.getPoint().getX() - first.getPoint().getY() )
                + (second.getPoint().getY() - first.getPoint().getY())
                *(second.getPoint().getY() - first.getPoint().getY()));

    }
}
