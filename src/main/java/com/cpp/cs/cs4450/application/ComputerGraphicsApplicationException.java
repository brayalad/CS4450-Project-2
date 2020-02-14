package com.cpp.cs.cs4450.application;

/**
 * Exception class to be thrown by the {@link com.cpp.cs.cs4450.application.ComputerGraphicsApplication} class.
 */
public class ComputerGraphicsApplicationException extends RuntimeException {

    /**
     * Constructor
     *
     * @param e An exception
     */
    public ComputerGraphicsApplicationException(final Exception e){
        super(e);
    }

    /**
     * Constructor
     * @param e Error message.
     */
    public ComputerGraphicsApplicationException(final String e){
        super(e);
    }

}