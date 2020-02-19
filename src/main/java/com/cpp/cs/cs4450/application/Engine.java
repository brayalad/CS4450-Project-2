/***************************************************************
 * file: Engine.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: interface responsible for running all the logic for a program.
 *
 ****************************************************************/

package com.cpp.cs.cs4450.application;

/**
 * This is the Engine interface responsible for running all the logic for a program.
 * Everything that happens in the program starts and is controlled by the Engine.
 */
public interface Engine extends Runnable {

    /**
     * Starts the program the Engine is running.
     */
    void start();

    /**
     * Shuts down the engine
     */
    void shutdown();

}
