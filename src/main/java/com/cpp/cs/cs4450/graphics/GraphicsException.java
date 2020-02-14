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
