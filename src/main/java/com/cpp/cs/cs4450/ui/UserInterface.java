package com.cpp.cs.cs4450.ui;

import java.util.Arrays;

/**
 * Interface for the user to interact with the program,
 */
public interface UserInterface {

    String DEFAULT_STRING_JOIN_DELIMITER = " ";

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

    static void println(String ...s){
        Arrays.stream(s).forEach(UserInterface::println);
    }

    static void print(String ...s){
        print(String.join(DEFAULT_STRING_JOIN_DELIMITER, s));
    }

    static void println(String s){
        System.out.println(s);
    }

    static void print(String s){
        System.out.print(s);
    }

    static void println(){
        System.out.println();
    }

}