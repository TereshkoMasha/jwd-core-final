package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.domain.factory.EntityFactory;
import com.epam.jwd.core_final.domain.factory.CrewMemberFactory;
import com.epam.jwd.core_final.domain.factory.impl.SpaceshipFactory;

import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputReaderUtil {
    ApplicationProperties properties;

    public InputReaderUtil(ApplicationProperties properties) {
        this.properties = properties;
    }

    public void readInputFile(Collection<CrewMember> collection, Collection<Spaceship> spaceships) throws InvalidStateException {

        EntityFactory<CrewMember> crewFactory = new CrewMemberFactory();
        EntityFactory<Spaceship> spaceshipFactory = new SpaceshipFactory();

        Scanner scanner = new Scanner(Objects.requireNonNull(
                Main.class.getClassLoader().getResourceAsStream(properties.getInputRootDir()
                        + "/" + properties.getCrewFileName())));
        while (scanner.hasNext()) {
            Pattern pattern = Pattern.compile("#((\\w+)\\,(\\w+)\\,(\\w+);)");
            Matcher matcher = pattern.matcher(scanner.nextLine());
            if (matcher.find()) {
                Pattern pattern1 = Pattern.compile("(\\w+),(\\w+ \\w+),(\\w);");
                matcher = pattern1.matcher(scanner.nextLine());
                while (matcher.find()) {
                    collection.add(crewFactory.create(matcher.group(1), matcher.group(2), matcher.group(3)));
                }
            } else {
                throw new InvalidStateException("Invalid crew.txt input");
            }
        }
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
                        spaceships.add(spaceshipFactory.create(matcher.group(1),matcher.group(2),matcher.group(3),
                                matcher.group(4),matcher.group(5),matcher.group(6)));
                    }
                }
            } else {
                throw new InvalidStateException("Invalid spaceship.txt input");
            }
        }

    }

}
