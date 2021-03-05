package com.epam.jwd.core_final.comand.impl;

import com.epam.jwd.core_final.comand.Command;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceMapServiceImpl;

import java.io.IOException;

public class SetPlanetsOnMission implements Command {

    @Override
    public void execute() {


        System.out.println("\nOk let's pick two planets: ");
        Planet departure = SpaceMapServiceImpl.getInstance().getRandomPlanet();
        Planet arrival = SpaceMapServiceImpl.getInstance().getRandomPlanet();
        System.out.println("Planets: " + departure.getName() + " && " + arrival.getName() + " selected");

        MissionServiceImpl.getInstance().setPlanetsOnMission(departure, arrival);

    }
}
