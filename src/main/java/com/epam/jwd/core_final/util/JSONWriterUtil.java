package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TimerTask;

public final class JSONWriterUtil extends TimerTask {

    private File planet;
    private File crew;
    private File missions;
    private File spaceships;


    public JSONWriterUtil(ApplicationProperties applicationProperties) {
        refresh(applicationProperties);
    }

    private void makeFile(ApplicationProperties properties) {
        planet = new File("src/main/resources/" + properties.getOutputRootDir()
                + "\\" + properties.getPlanetFileName() + ".json");
        crew = new File("src/main/resources/" + properties.getOutputRootDir()
                + "\\" + properties.getCrewFileName() + ".json");
        missions = new File("src/main/resources/" + properties.getOutputRootDir()
                + "\\" + properties.getMissionsFileName() + ".json");
        spaceships = new File("src/main/resources/" + properties.getOutputRootDir()
                + "\\" + properties.getSpaceshipsFileName() + ".json");
    }

    public void refreshJSON() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(missions, NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class));
        mapper.writeValue(spaceships, NasaContext.getInstance().retrieveBaseEntityList(Spaceship.class));
        mapper.writeValue(planet, NasaContext.getInstance().retrieveBaseEntityList(Planet.class));
        mapper.writeValue(crew, NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class));

    }

    @SneakyThrows
    @Override
    public void run() {
        refreshJSON();
    }

    public void refresh(ApplicationProperties properties) {
        makeFile(properties);
    }
}
