package com.epam.jwd.core_final.domain.factory.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.factory.EntityFactory;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.time.LocalDateTime;

public class FlightMissionFactory implements EntityFactory<FlightMission> {

    @Override
    public FlightMission create(Object... objects) throws InvalidStateException {

        String name = objects[0].toString();
        LocalDateTime localDate = (LocalDateTime) objects[1];
        LocalDateTime localDate1 = (LocalDateTime) objects[2];
        Long distance = Long.parseLong(objects[3].toString());

//        Spaceship spaceship = null;
//        for (Spaceship space :
//                NasaContext.getInstance().retrieveBaseEntityList(Spaceship.class)) {
//            if (space.getFlightDistance() >= distance && space.getIsReadyForNextMissions()) {
//                spaceship = space;
//                break;
//            }
//        }
//        if(spaceship == null){
//            throw new InvalidStateException("There is no available spaceship");
//        }
//        List<CrewMember> crewMembers =
//                NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
//                        .stream()
//                        .filter(CrewMember::getIsReadyForNextMissions)
//                        .collect(Collectors.toList());
//
//        List<CrewMember> assignedCrew = new ArrayList<>();
//
//        for (Map.Entry<Role, Short> entry
//                : spaceship.getCrew().entrySet()) {
//            for (int m = 0, i = 0; m< crewMembers.size();  m++) {
//                if (i < entry.getValue()) {
//                    if (crewMembers.get(m).getRole() == entry.getKey() && crewMembers.get(m).getIsReadyForNextMissions()) {
//                        crewMembers.get(m).setIsReadyForNextMissions(false);
//                        assignedCrew.add(crewMembers.get(m));
//                        i++;
//                    }
//                }else {
//                    break;
//                }
//            }
//
//        }
        return new FlightMission(name, localDate, localDate1, distance, MissionResult.PLANNED);
    }
}
