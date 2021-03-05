package com.epam.jwd.core_final.comand.impl;

import com.epam.jwd.core_final.comand.Command;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import org.apache.log4j.Logger;

import java.security.InvalidAlgorithmParameterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CreateFlightMission implements Command {
    private static final Logger logger = Logger.getLogger(CreateFlightMission.class);
    Scanner scanner = new Scanner(System.in);


    public CreateFlightMission() throws InvalidStateException {
        execute();
    }

    @Override
    public void execute() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            System.out.print("\nEnter mission name: ");
            String name = scanner.nextLine();
            System.out.print("Enter start date (yyyy-MM-dd HH:mm:ss): ");
            String date = scanner.nextLine();
            LocalDateTime localDateStart = LocalDateTime.parse(date, formatter);
            System.out.print("Enter start date (yyyy-MM-dd HH:mm:ss): ");
            date = scanner.nextLine();
            LocalDateTime localDateEnd = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.print("Enter mission distance: ");
            Long distance = scanner.nextLong();

            NasaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                    .add(MissionServiceImpl.getInstance().createMission(new FlightMissionFactory().create(name, localDateStart, localDateEnd, distance, MissionResult.PLANNED)));
            System.out.println("Success: mission " + name + " added");
        } catch (Exception e) {
            System.out.println("Invalid input, try again");
            logger.error(e, new InvalidAlgorithmParameterException());
        }
    }
}
