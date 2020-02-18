package com.cpp.cs.cs4450.application;

import com.cpp.cs.cs4450.graphics.GraphicsEngine;
import com.cpp.cs.cs4450.models.shapes.DisplayShape;
import com.cpp.cs.cs4450.models.shapes.Polygon;
import com.cpp.cs.cs4450.ui.UserInterface;
import com.cpp.cs.cs4450.util.factory.PolygonFactory;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This is the programs implementation of the {@link com.cpp.cs.cs4450.application.Engine} interface.
 * All the logic for the program is contained in this class.
 */
public final class EngineImpl implements Engine {

    private static final Random RANDOM = new Random();

    /**
     * The {@link com.cpp.cs.cs4450.graphics.GraphicsEngine} that is being used by
     * the program to render graphics onto the display.
     */
    private final GraphicsEngine graphicsEngine;

    /**
     * The {@link com.cpp.cs.cs4450.ui.UserInterface} that is used to communicate
     * with the user during the program.
     */
    private final UserInterface userInterface;


    /**
     * Constructor
     *
     * @param graphicsEngine The {@link com.cpp.cs.cs4450.graphics.GraphicsEngine} implementation to be used by the engine.
     * @param userInterface The {@link com.cpp.cs.cs4450.ui.UserInterface} implementation to be used by the engine.
     */
    public EngineImpl(final GraphicsEngine graphicsEngine, final UserInterface userInterface) {
        this.graphicsEngine = graphicsEngine;
        this.userInterface = userInterface;
    }

    /**
     * Starts the program the Engine is running.
     */
    @Override
    public void start() {
        run();
    }

    /**
     * This method contains the loop that runs the program. The loop will stop running if the
     * Display is closed or if the user has ended the program.
     */
    @Override
    public void run() {
        while(!graphicsEngine.displayCloseRequested() && !userInterface.endProgramSignal()){
            if(userInterface.changeColorSignal()){
                changeColor();
            }

            if(userInterface.changeColorToDefaultSignal()){
                graphicsEngine.changeColors(null);
            }

            graphicsEngine.render();
        }

        shutdown();
    }

    /**
     * Shuts down the engine
     */
    @Override
    public void shutdown() {
        userInterface.shutdown();
        graphicsEngine.shutdown();
        System.exit(0);
    }


    private void changeColor(){
        final int timesToShuffle = RANDOM.nextInt(10);

        final List<Color> supportedColors = new ArrayList<>(PolygonFactory.SUPPORTED_COLORS);
        for(int i = 0; i <= timesToShuffle; ++i){
            Collections.shuffle(supportedColors);
        }

        final Queue<Color> colorQueue = new ArrayDeque<>(supportedColors);

        final List<DisplayShape> displayShapes = graphicsEngine.getRenders().stream()
                .filter(r -> r instanceof DisplayShape)
                .map(r -> (DisplayShape) r)
                .collect(Collectors.toList());

        final Map<DisplayShape, Color> newColors = new HashMap<>();
        for(final DisplayShape displayShape : displayShapes){
            if(colorQueue.peek() == null) {
                Collections.shuffle(supportedColors);
                colorQueue.addAll(supportedColors);
            }

            newColors.put(displayShape, colorQueue.poll());
        }

        graphicsEngine.changeColors(newColors);
    }

}
