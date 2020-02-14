package com.cpp.cs.cs4450.ui;

/**
 * Exception class to be thrown by the {@link com.cpp.cs.cs4450.ui.UserInterface}.
 */
public class UserInterfaceException extends RuntimeException {

    /**
     * Constructor
     *
     * @param e An exception
     */
    public UserInterfaceException(final Exception e){
        super(e);
    }

    /**
     * Constructor
     *
     * @param e Error message.
     */
    public UserInterfaceException(final String e){
        super(e);
    }

}
