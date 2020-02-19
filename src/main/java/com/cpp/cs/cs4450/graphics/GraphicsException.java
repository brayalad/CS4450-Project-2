/***************************************************************
 * file: GraphicsException.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Exception to be thrown by GraphicsEngine
 *
 ****************************************************************/

package com.cpp.cs.cs4450.graphics;


/**
 * Exception class to be thrown by the {@link com.cpp.cs.cs4450.graphics.GraphicsEngine}.
 */
public class GraphicsException extends RuntimeException {

    /**
     * Constructor
     *
     * @param e An exception
     */
    public GraphicsException(final Exception e){
        super(e);
    }

    /**
     * Constructor
     *
     * @param e Error message.
     */
    public GraphicsException(final String e){
        super(e);
    }

}
