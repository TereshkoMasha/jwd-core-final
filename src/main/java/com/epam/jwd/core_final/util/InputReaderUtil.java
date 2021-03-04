package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.domain.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.domain.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.domain.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.domain.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputReaderUtil {

    private final Logger logger = Logger.getLogger(InputReaderUtil.class);
    private final ApplicationProperties properties;
    private Scanner scanner;

    public InputReaderUtil(ApplicationProperties properties) {
        this.properties = properties;
    }

    private void readCrewFile() throws InvalidStateException {
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
                            .add(new CrewMemberFactory().create(Role.resolveRoleById(Integer.parseInt(matcher.group(1))),
                                    matcher.group(2), Rank.resolveRankById(Integer.parseInt(matcher.group(3)))));
                    ;
                }
            } else {

                throw new InvalidStateException("Invalid" + properties.getCrewFileName() + ".txt input");
            }
        }
        logger.info("Processed " + properties.getCrewFileName() + ".txt file");
    }

    private void readSpaceshipFile() throws InvalidStateException {
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
                        NasaContext.getInstance().retrieveBaseEntityList(Spaceship.class).add(new SpaceshipFactory()
                                .create(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4),
                                        matcher.group(5), matcher.group(6)));
                    }
                }
            } else {
                throw new InvalidStateException("Invalid " + properties.getSpaceshipsFileName() + ".txt input");
            }
        }
        logger.info("Processed " + properties.getSpaceshipsFileName() + ".txt file");
    }

    private void readMissionsFile() throws InvalidStateException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(properties.getDataTimeFormat());
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
                                .add(new FlightMissionFactory()
                                        .create(matcher.group(1)
                                                , LocalDateTime.parse(matcher.group(2), formatter)
                                                , LocalDateTime.parse(matcher.group(3), formatter)
                                                , matcher.group(4)));
                    }
                }
            }
        }
        logger.info("Processed " + properties.getMissionsFileName() + ".txt file");
    }

    private void readPlanetFile() {
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
                    NasaContext.getInstance().retrieveBaseEntityList(Planet.class).add(new PlanetFactory().create(x, y, name));
                }
            }
        }
        logger.info("Processed " + properties.getPlanetFileName() + ".txt file");
    }


    public void readInputFile() throws InvalidStateException {
        readCrewFile();
        readSpaceshipFile();
        readMissionsFile();
        readPlanetFile();
    }
}
