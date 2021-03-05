package com.epam.jwd.core_final.comand.impl;

import com.epam.jwd.core_final.comand.Command;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.domain.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.util.Scanner;

public class CreateSpaceship implements Command {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        try {
            short flightEng = 0;
            short missionSp = 0;
            short pilots = 0;
            short commanders = 0;

            System.out.print("\nEnter spaceship name: ");
            String name = scanner.next();
            System.out.print("Enter flight distance: ");
            Integer flightDistance = scanner.nextInt();

            for (Role role :
                    Role.values()) {
                if (role == Role.MISSION_SPECIALIST) {
                    System.out.print("How many mission specialists will be assigned to the ship? : ");
                    missionSp = scanner.nextShort();
                }
                if (role == Role.FLIGHT_ENGINEER) {
                    System.out.print("How many flight engineers will be assigned to the ship? : ");
                    flightEng = scanner.nextShort();
                }
                if (role == Role.PILOT) {
                    System.out.print("How many pilots will be assigned to the ship? : ");
                    pilots = scanner.nextShort();
                }
                if (role == Role.COMMANDER) {
                    System.out.print("How many commanders will be assigned to the ship? : ");
                    commanders = scanner.nextShort();
                }
            }
            NasaContext.getInstance().retrieveBaseEntityList(Spaceship.class).add(SpaceshipServiceImpl.getInstance().createSpaceship(
                    new SpaceshipFactory().create(name, flightDistance, missionSp, flightEng, pilots, commanders)));

            System.out.println("Success: crew " + name + " added\n");

        } catch (Exception e) {
            System.out.println("Invalid input, try again");
        }
    }
}
