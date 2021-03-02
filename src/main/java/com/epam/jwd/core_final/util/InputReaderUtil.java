package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.domain.factory.EntityFactory;
import com.epam.jwd.core_final.domain.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.domain.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.domain.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.domain.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputReaderUtil {
    private final ApplicationProperties properties;
    private Scanner scanner;


    public InputReaderUtil(ApplicationProperties properties) {
        this.properties = properties;
    }

    private void readCrewFile() throws InvalidStateException {

        File file = new File("src/main/resources/" + properties.getOutputRootDir()
                + "\\" + properties.getCrewFileName() + ".json");

        EntityFactory<CrewMember> crewFactory = new CrewMemberFactory();
        scanner = new Scanner(Objects.requireNonNull(
                Main.class.getClassLoader().getResourceAsStream(properties.getInputRootDir()
                        + "/" + properties.getCrewFileName())));

        while (scanner.hasNext()) {
            Pattern pattern = Pattern.compile("#((\\w+)\\,(\\w+)\\,(\\w+);)");
            Matcher matcher = pattern.matcher(scanner.nextLine());
            if (matcher.find()) {
                Pattern pattern1 = Pattern.compile("(\\w+),(\\w+ \\w+),(\\w);");
                matcher = pattern1.matcher(scanner.nextLine());
                while (matcher.find()) {
                    NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                            .add(crewFactory.create(matcher.group(1), matcher.group(2), matcher.group(3)));
                    ;
                }
                refreshJSON(file, CrewMember.class);
            } else {
                throw new InvalidStateException("Invalid crew.txt input");
            }
        }

    }

    private void readSpaceshipFile() throws InvalidStateException {
        File file = new File("src/main/resources/" + properties.getOutputRootDir()
                + "\\" + properties.getSpaceshipsFileName() + ".json");

        EntityFactory<Spaceship> spaceshipFactory = new SpaceshipFactory();
        scanner = new Scanner(Objects.requireNonNull(
                Main.class.getClassLoader().getResourceAsStream(properties.getInputRootDir()
                        + "/" + properties.getSpaceshipsFileName())));

        while (scanner.hasNext()) {
            Pattern pattern = Pattern.compile("#\\w+;\\w+;\\w+ \\W(\\w+:\\w+),\\w+:\\w+,\\w+:\\w+,\\w+:\\w+\\W");
            Matcher matcher = pattern.matcher((scanner.nextLine()));
            if (matcher.find()) {
                Pattern pattern1 = Pattern.compile("(\\w+|\\w+ \\w+);(\\w+);\\W1:([1-9]),2:([1-9]),3:([1-9]),4:([1-9])\\W");
                while (scanner.hasNext()) {
                    matcher = pattern1.matcher(scanner.nextLine());
                    if (matcher.find()) {
                        NasaContext.getInstance().retrieveBaseEntityList(Spaceship.class).add(spaceshipFactory
                                .create(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4),
                                        matcher.group(5), matcher.group(6)));
                    }
                }
                refreshJSON(file, Spaceship.class);
            } else {
                throw new InvalidStateException("Invalid spaceship.txt input");
            }
        }

    }

    private void readMissionsFile() throws InvalidStateException {
        File file = new File("src/main/resources/" + properties.getOutputRootDir()
                + "\\" + properties.getMissionsFileName() + ".json");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(properties.getDataTimeFormat());
        EntityFactory<FlightMission> flightMissionFactory = new FlightMissionFactory();
        scanner = new Scanner(Objects.requireNonNull(
                Main.class.getClassLoader().getResourceAsStream(properties.getInputRootDir()
                        + "/" + properties.getMissionsFileName())));
        while (scanner.hasNext()) {
            Pattern pattern = Pattern.compile("#\\w+;\\w+,\\w+;\\w+;\\w+;");
            Matcher matcher = pattern.matcher(scanner.nextLine());
            if (matcher.find()) {
                pattern = Pattern.compile("(\\w+);(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}),(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2});(\\w+)");
                while (scanner.hasNext()) {
                    matcher = pattern.matcher(scanner.nextLine());
                    while (matcher.find()) {
                        NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                                .add(flightMissionFactory
                                        .create(matcher.group(1)
                                                , LocalDateTime.parse(matcher.group(2), formatter)
                                                , LocalDateTime.parse(matcher.group(3), formatter)
                                                , matcher.group(4)));
                    }
                }
                refreshJSON(file, FlightMission.class);
            }
        }
    }

    private void readPlanetFile() throws InvalidStateException {
        File file = new File("src/main/resources/" + properties.getOutputRootDir()
                + "\\" + properties.getPlanetFileName() + ".json");

        EntityFactory<Planet> planetFactory = new PlanetFactory();

        int y = 0;
        int x = 0;
        scanner = new Scanner(Objects.requireNonNull(
                Main.class.getClassLoader().getResourceAsStream(properties.getInputRootDir()
                        + "/" + properties.getPlanetFileName())));
        while (scanner.hasNext()) {

            String line = scanner.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            while (tokenizer.hasMoreTokens()) {
                String name = tokenizer.nextToken();
                x += name.length() + 1;
                if (x > line.length()) {
                    y++;
                    x = 0;
                }
                if (!name.equals("null")) {
                    NasaContext.getInstance().retrieveBaseEntityList(Planet.class).add(planetFactory.create(x, y, name));
                }
            }
        }
        refreshJSON(file, Planet.class);
    }

    private void refreshJSON(File file, Class<? extends BaseEntity> tClass) {
        Collection<? extends BaseEntity> entities = NasaContext.getInstance().retrieveBaseEntityList(tClass);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.valueToTree(entities);
            mapper.writeValue(file, node);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public void readInputFile() throws InvalidStateException {
        readPlanetFile();
        readCrewFile();
        //readSpaceshipFile();
        //readMissionsFile();
    }
}
