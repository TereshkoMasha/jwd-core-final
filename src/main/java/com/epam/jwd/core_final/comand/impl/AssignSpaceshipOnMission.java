package com.epam.jwd.core_final.comand.impl;

import com.epam.jwd.core_final.comand.Command;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.util.Optional;
import java.util.Scanner;

public class AssignSpaceshipOnMission implements Command {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        try {
            SpaceshipCriteria.SpaceshipCriteriaBuilder builder = new SpaceshipCriteria.SpaceshipCriteriaBuilder();
            System.out.println("\nLet's find the right spaceship");
            System.out.print("Enter all spaceship criteria: ");
            System.out.println("(you can find spaceship by)\n" +
                    "1. Name\n" +
                    "2. ID\n" +
                    "3. Flight distance\n");
            int inx = 0;
            while (inx != -1) {
                System.out.print("Enter command (enter 1-3 to input criteria, 0 - to end input): ");
                switch (scanner.nextInt()) {
                    case 1: {
                        System.out.print("Input name: ");
                        String name = scanner.next();
                        builder.setName(name);
                        break;
                    }
                    case 2: {
                        System.out.print("Input id: ");
                        Long id = scanner.nextLong();
                        builder.setId(id);
                        break;
                    }
                    case 3: {
                        System.out.print("Flight distance: ");
                        Long distance = scanner.nextLong();
                        builder.setFlightDistance(distance);
                        break;
                    }
                    case 0: {
                        inx = -1;
                    }
                }
            }
            SpaceshipCriteria criteria = builder.createSpaceshipCriteria();
            Optional<Spaceship> spaceship = SpaceshipServiceImpl.getInstance().findSpaceshipByCriteria(criteria);
            if (spaceship.isPresent()) {
                spaceship.ifPresent(value -> MissionServiceImpl.getInstance().assignSpaceshipOnMission(value));
            } else {
                System.out.println("The spaceship was not found according to these criteria");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}