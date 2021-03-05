package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.context.impl.NasaMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.function.Supplier;


public interface Application {


    static ApplicationMenu start() throws InvalidStateException {

        final Supplier<ApplicationContext> applicationContextSupplier = () -> (ApplicationContext) new NasaMenu();

        final NasaContext nasaContext = new NasaContext();
        nasaContext.init();

        return applicationContextSupplier::get;
    }
}
