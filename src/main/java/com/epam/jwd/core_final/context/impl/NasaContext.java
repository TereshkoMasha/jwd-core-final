package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
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

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        switch (tClass.getSimpleName()) {
            case "CrewMember": {
                return (Collection<T>) crewMembers;
            }
            case "Spaceship": {
                return (Collection<T>) spaceships;
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
        inputReaderUtil.readInputFile(crewMembers, spaceships);


    }

    @Override
    public ApplicationContext getApplicationContext() {
        return instance;
    }
}
