package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.comand.impl.CreateCrewCommand;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
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

    default void menu() {
        Scanner scanner = new Scanner(System.in);
        CrewService service = new CrewServiceImpl();
        while (true) {
            switch (scanner.next()) {
                case "-FC": {  //find crew
                    Criteria<CrewMember> criteria = new CrewMemberCriteria
                            .CrewMemberCriteriaBuilder().setRank(Rank.resolveRankById(scanner.nextInt())).build();
                    service.findCrewMemberByCriteria(criteria);
                    break;
                }
                case "-AC": {
                    new CreateCrewCommand();
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
