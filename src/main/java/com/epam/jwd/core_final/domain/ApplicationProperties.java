package com.epam.jwd.core_final.domain;

import lombok.Getter;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public final class ApplicationProperties {
    //todo
    @Getter
    private final String inputRootDir;
    @Getter
    private final String outputRootDir;
    @Getter
    private final String crewFileName;
    @Getter
    private final String missionsFileName;
    @Getter
    private final String spaceshipsFileName;
    @Getter
    private final Integer fileRefreshRate;  //частота обновления файла
    @Getter
    private final String dataTimeFormat;

    public ApplicationProperties(String inputRootDir, String outputRootDir, String crewFileName,
                                 String missionsFileName, String spaceshipsFileName,
                                 Integer fileRefreshRate, String dataTimeFormat) {
        this.inputRootDir = inputRootDir;
        this.outputRootDir = outputRootDir;
        this.crewFileName = crewFileName;
        this.missionsFileName = missionsFileName;
        this.spaceshipsFileName = spaceshipsFileName;
        this.fileRefreshRate = fileRefreshRate;
        this.dataTimeFormat = dataTimeFormat;
    }


}
