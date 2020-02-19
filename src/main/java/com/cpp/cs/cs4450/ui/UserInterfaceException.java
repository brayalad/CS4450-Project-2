/***************************************************************
 * file: UserInterfaceException.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Exception to be thrown by the user interface
 *
 ****************************************************************/

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
