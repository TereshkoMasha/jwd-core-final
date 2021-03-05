package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReaderUtil {
    private static final Logger logger = Logger.getLogger(PropertyReaderUtil.class);

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
                , properties.getProperty("dateTimeFormat")
                , properties.getProperty("planetFileName"));
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
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(inputStream);
            readProperties();
            logger.info("Properties loaded");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
