package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class NasaMenu implements ApplicationMenu {

    @Override
    public ApplicationContext getApplicationContext() {
        return NasaContext.getInstance();
    }
    public void menu()  { }

    @Override
    public String printAvailableOptions() {
        return "1.Print all crew members -PC\n" +
                "2.Print all spaceships -PS\n" +
                "3.Print all missions -PM\n" +
                "4.Add crew member -AC\n";
    }
}
