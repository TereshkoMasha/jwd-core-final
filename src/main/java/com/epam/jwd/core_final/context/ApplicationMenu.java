package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import org.apache.log4j.Logger;

import java.util.Scanner;

// todo replace Object with your own types

@FunctionalInterface
public interface ApplicationMenu {

    Logger logger = Logger.getLogger(ApplicationMenu.class);
    ApplicationContext getApplicationContext();

    default String printAvailableOptions() {
        return "1.Print all crew members -PC\n" +
                "2.Print all spaceships -PS\n" +
                "3.Print all missions -PM\n" +
                "4.Add crew member -AC\n";
    }

    default void menu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        CrewService service = new CrewServiceImpl();
        while (true) {
            switch (scanner.next()) {
                case "-PC": { //print all crews
                    CrewServiceImpl.instance.findAllCrewMembers();
                }
                case "-FC": {  //find crew
                    Criteria<CrewMember> criteria = new CrewMemberCriteria
                            .CrewMemberCriteriaBuilder().setRank(Rank.resolveRankById(scanner.nextInt())).build();
                    service.findCrewMemberByCriteria(criteria);
                    break;
                }
                case "-AC": {
                    System.out.println("Enter crew name: ");
                    String name = scanner.next();
                    System.out.println("Enter crew rank");
                    Rank rank = Rank.resolveRankById(Integer.parseInt(scanner.next()));
                    System.out.println("Enter crew role");
                    Role role = Role.resolveRoleById(scanner.nextInt());
                    CrewMember crewMember = service.createCrewMember(new CrewMemberFactory().create(role, name, rank));
                    NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class).add(crewMember);
                    break;
                }
                default: {
                    logger.error("?", new UnknownEntityException("Unknown command input"));
                }
            }
        }
    }

    default Object handleUserInput(Object o) {
        return null;
    }
}
