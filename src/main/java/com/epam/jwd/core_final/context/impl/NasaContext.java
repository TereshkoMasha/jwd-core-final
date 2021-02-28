package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.util.InputReaderUtil;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

// todo
public class NasaContext implements ApplicationContext, ApplicationMenu {

    @Getter
    private static final NasaContext instance = new NasaContext();

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<FlightMission> missions = new ArrayList<>();
    private Collection<Planet> planetMap = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        switch (tClass.getSimpleName()) {
            case "CrewMember": {
                return (Collection<T>) crewMembers;
            }
            case "Spaceship": {
                return (Collection<T>) spaceships;
            }
            case "FlightMission": {
                return (Collection<T>) missions;
            }
            case "Planet": {
                return (Collection<T>) planetMap;
            }
            default: {
                throw new UnknownEntityException("Invalid entity name");
            }
        }
    }

    /**
     * You have to read input files, populate collections
     *
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {

        PropertyReaderUtil propertyReaderUtil = new PropertyReaderUtil();
        ApplicationProperties properties = propertyReaderUtil.readProperties();

        InputReaderUtil inputReaderUtil = new InputReaderUtil(properties);
        inputReaderUtil.readInputFile();


    }

    @Override
    public ApplicationContext getApplicationContext() {
        return NasaContext.getInstance();
    }
}
