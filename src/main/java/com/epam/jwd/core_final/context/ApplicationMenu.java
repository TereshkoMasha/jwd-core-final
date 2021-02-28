package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.AbstractBaseEntity;

// todo replace Object with your own types

@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    default String printAvailableOptions() {
        return "1.Print all crew members -PC\n" +
                "2.Print all spaceships -PS\n" +
                "3.Print all missions -PM\n" +
                "4.Add crew member -AC\n";
    }

    default AbstractBaseEntity handleUserInput(Object o) {
        return null;
    }
}
