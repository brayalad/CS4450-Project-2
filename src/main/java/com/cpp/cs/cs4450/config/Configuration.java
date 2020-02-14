package com.cpp.cs.cs4450.config;

import com.cpp.cs.cs4450.util.os.OperatingSystem;
import com.cpp.cs.cs4450.util.os.OperatingSystem.OperatingSystemUtils;
import org.lwjgl.opengl.DisplayMode;


import java.nio.file.Paths;

/**
 * This is the configuration class that is used by the {@link com.cpp.cs.cs4450.application.ComputerGraphicsApplication} class
 * to launch the program.
 */
public final class Configuration {

    /**
     * The programs title.
     */
    public static final String PROGRAM_TITLE = "CS 4450 Program 2";

    /**
     * The current working directory of the project
     */
    public static final String CURRENT_WORKING_DIRECTORY_PATH = Paths.get(".").toAbsolutePath().normalize().toString();

    /**
     * OS being used.
     */
    private static final OperatingSystem OPERATING_SYSTEM = OperatingSystemUtils.getOS();

    /**
     * Default file path for coordinates.txt file
     */
    private static final String DEFAULT_COORDINATES_FILE_PATH = "+src+main+java+com+cpp+cs+cs4450+coordinates.txt";

    /**
     * The Default file path to be used.
     */
    public static final String COORDINATES_FILE_PATH = CURRENT_WORKING_DIRECTORY_PATH + DEFAULT_COORDINATES_FILE_PATH.replaceAll("\\+", OPERATING_SYSTEM.getDelimiter());

    /**
     * default file path for OS natives
     */
    public static final String LWJGL_LIBRARY_NATIVES_DEFAULT_FILE_PATH = "+libs+lwjgl-2.9.2+native+";

    /**
     * File path for OS Natives
     */
    public static final String LWJGL_LIBRARY_NATIVES_FILE_PATH = CURRENT_WORKING_DIRECTORY_PATH + LWJGL_LIBRARY_NATIVES_DEFAULT_FILE_PATH.replaceAll("\\+", OPERATING_SYSTEM.getDelimiter()) + OPERATING_SYSTEM.getName();

    /**
     * The display windows default horizontal size;
     */
    public static final int DEFAULT_HORIZONTAL_WINDOW_SIZE = 640;

    /**
     * The display windows default vertical size.
     */
    public static final int DEFAULT_VERTICAL_WINDOW_SIZE = 480;

    /**
     * Method that creates a new default display mode.
     *
     * @return A default display mode.
     */
    public static DisplayMode displayMode(){
        return new DisplayMode(DEFAULT_HORIZONTAL_WINDOW_SIZE, DEFAULT_VERTICAL_WINDOW_SIZE);
    }

}
