package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import java.io.IOException;
import java.util.Properties;

public final class PropertyReaderUtil {

    private static final Properties properties = new Properties();

    public PropertyReaderUtil() {
         loadProperties();
    }

    public ApplicationProperties readProperties() {
        return new ApplicationProperties(
                properties.getProperty("inputRootDir")
                , properties.getProperty("outputRootDir")
                , properties.getProperty("crewFileName")
                , properties.getProperty("missionsFileName")
                , properties.getProperty("spaceshipsFileName")
                , Integer.parseInt(properties.getProperty("fileRefreshRate"))
                , properties.getProperty("dateTimeFormat"));
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */


    private void loadProperties() {
        try {
            properties.load(Main.class.getClassLoader().getResourceAsStream("application.properties"));
            readProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
