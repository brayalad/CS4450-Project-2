package com.cpp.cs.cs4450.application;

import com.cpp.cs.cs4450.config.Configuration;
import com.cpp.cs.cs4450.graphics.GraphicsEngine;
import com.cpp.cs.cs4450.graphics.LWJGLGraphicsEngine;
import com.cpp.cs.cs4450.graphics.Renderable;
import com.cpp.cs.cs4450.models.shapes.Polygon;
import com.cpp.cs.cs4450.ui.LWJGLUserInterface;
import com.cpp.cs.cs4450.ui.UserInterface;
import com.cpp.cs.cs4450.util.factory.PolygonFactory;
import com.cpp.cs.cs4450.util.io.PolygonCoordinateFileParser;
import com.cpp.cs.cs4450.util.io.PolygonCoordinateFileParser.ParsedFilePolygon;
import org.lwjgl.opengl.DisplayMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The ComputerGraphicsApplication class is an abstract class that is to be extended by any class
 * that contains the main method for a program that displays computer graphics to a display.
 * This class uses values from the {@link com.cpp.cs.cs4450.config.Configuration} class.
 */
public abstract class ComputerGraphicsApplication {
    /**
     * The file path flag used to check if the user passed a file path to the coordinates.txt file
     * in the command line arguments.
     */
    private static final String FILE_PATH_FLAG = "-f";

    /**
     * File path error message format
     */
    private static final String LINK_ERROR_MESSAGE_FORMAT = "JAVA VM Arguments should be set to: -Djava.library.path=%s";

    /**
     * Java Library Property Key
     */
    private static final String JAVA_LIBRARY_PATH_PROPERTY_KEY = "java.library.path";

    /**
     * This is the method that launches the Computer Graphics application. It is responsible
     * for parsing through the command line arguments and initializing objects used in the program
     * and injecting them into other objects as dependencies as needed.
     *
     * @param args command line arguments
     */
    public static void launch(final String ...args){
        if(args == null || args.length == 0)
            launch();

        assert args != null;
        final List<String> arguments = new ArrayList<>(Arrays.asList(args));

        final int indexOfFilePathFlag = arguments.indexOf(FILE_PATH_FLAG);

        launch(((indexOfFilePathFlag != -1) && ((indexOfFilePathFlag + 1) < arguments.size())) ? arguments.get(indexOfFilePathFlag + 1) : Configuration.COORDINATES_FILE_PATH);
    }

    /**
     * Overloaded {@link #launch(String...) launch} that launches application with default configuration of {@link com.cpp.cs.cs4450.config.Configuration#COORDINATES_FILE_PATH}.
     */
    public static void launch(){
        launch(Configuration.COORDINATES_FILE_PATH);
    }

    /**
     * Private overloaded {@link #launch(String...) launch} method that intialized the program based on
     * the coordinated.txt file.
     *
     * @param file The path to the coordinates.txt file
     */
    private static void launch(final String file){
        try {
            System.setProperty(JAVA_LIBRARY_PATH_PROPERTY_KEY, (Configuration.LWJGL_LIBRARY_NATIVES_FILE_PATH));

            final List<ParsedFilePolygon> parsedFilePolygons = PolygonCoordinateFileParser.parse(file);
            final List<Polygon> polygons = PolygonFactory.createPolygons(parsedFilePolygons);
            final List<Renderable> renders = new ArrayList<>(polygons);

            final DisplayMode displayMode = Configuration.displayMode();

            final GraphicsEngine graphicsEngine = new LWJGLGraphicsEngine(renders, displayMode, Configuration.PROGRAM_TITLE);
            final UserInterface userInterface = new LWJGLUserInterface();

            final Engine engine = new EngineImpl(graphicsEngine, userInterface);

            engine.start();
        } catch (IOException e) {
            throw new ComputerGraphicsApplicationException(e.getLocalizedMessage());
        } catch (LinkageError e){
            throw new UnsatisfiedLinkError(String.format(LINK_ERROR_MESSAGE_FORMAT, Configuration.LWJGL_LIBRARY_NATIVES_FILE_PATH));
        }
    }

}
