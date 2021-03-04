package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationMenu;


public class Main {


    public static void main(String[] args) throws Exception {

        ApplicationMenu menu = Application.start();
        System.out.println(menu.printAvailableOptions());
        menu.menu();

    }
}