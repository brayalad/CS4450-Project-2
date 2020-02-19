/***************************************************************
 * file: UserInterface.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Interface for user to use the program
 *
 ****************************************************************/

package com.cpp.cs.cs4450.ui;


/**
 * Interface for the user to interact with the program,
 */
public interface UserInterface {

    /**
     * Checks if the program has been signaled to end.
     *
     * @return true if the program was signal to end. False, otherwise.
     */
    boolean endProgramSignal();

    /**
     * Gets input from the user.
     *
     * @return user input
     */
    String getInput();

    /**
     * Checks if user wants to change graphics color
     *
     * @return true if user asks to change color, false otherwise
     */
    boolean changeColorSignal();

    /**
     * Checks if user wants to switch colors back to default.
     *
     * @return true if user asks to change color back to default, false otherwise
     */
    boolean changeColorToDefaultSignal();

    /**
     * Shuts down the user interface.
     */
    void shutdown();

}