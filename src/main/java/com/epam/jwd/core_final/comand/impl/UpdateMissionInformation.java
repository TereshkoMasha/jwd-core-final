package com.epam.jwd.core_final.comand.impl;

import com.epam.jwd.core_final.comand.Command;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

import java.util.Optional;
import java.util.Scanner;

public class UpdateMissionInformation implements Command {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("\nEntre mission name: ");
        String name = scanner.next();
        FlightMissionCriteria.FlightMissionCriteriaBuilder builder = new FlightMissionCriteria.FlightMissionCriteriaBuilder();
        builder.setName(name);
        FlightMissionCriteria criteria = builder.createFlightMissionCriteria();
        Optional<FlightMission> flightMission = MissionServiceImpl.getInstance().findMissionByCriteria(criteria);
        if (flightMission.isPresent()) {
            flightMission.ifPresent(flightMission1 -> {
                MissionServiceImpl.getInstance().updateMissionDetails(flightMission1);
            });
        }
    }
}
