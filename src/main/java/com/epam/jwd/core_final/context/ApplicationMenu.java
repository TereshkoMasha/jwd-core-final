package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.comand.impl.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

// todo replace Object with your own types

@FunctionalInterface
public interface ApplicationMenu {

    Logger logger = Logger.getLogger(ApplicationMenu.class);

    ApplicationContext getApplicationContext();

    default String printAvailableOptions() {
        return "1.Add crew member -AC\n" +
                "2.Add flight mission -AM\n" +
                "3.Add spaceship -AS\n" +
                "4.Assign spaceship on mission -MS\n" +
                "5.Assign crew on mission -MC\n" +
                "6.Set planets on mission -SP\n" +
                "7.Update mission information -UM\n" +
                "10. Exit -E";

    }

    default void menu() throws InvalidStateException, IOException {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println(printAvailableOptions());
            System.out.print("Enter the command: ");
            switch (scanner.next()) {
                case "-AC": {
                    new CreateCrewCommand().execute();
                    break;
                }
                case "-AM": {
                    new CreateFlightMission();
                    break;
                }
                case "-AS": {
                    new CreateSpaceship().execute();
                    break;
                }
                case "-MS": {
                    new AssignSpaceshipOnMission().execute();
                    break;
                }
                case "-MC": {
                    new AssignCrewOnMission().execute();
                    break;
                }
                case "-SP": {
                    new SetPlanetsOnMission().execute();
                    break;
                }
                case "-UM": {
                    new UpdateMissionInformation().execute();
                    break;
                }
                case "-E": {
                    flag = false;
                }
                default: {
                    logger.error(new UnknownEntityException("Unknown command input"));
                }

            }
        }
    }

    default Object handleUserInput(Object o) {
        return null;
    }
}
