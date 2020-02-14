package com.cpp.cs.cs4450.ui;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

/**
 * This is an impementation of the {@link UserInterface} interface that uses the
 * Lightweight Java Game Library. See <a href="http://legacy.lwjgl.org/index.php.html">http://legacy.lwjgl.org/index.php.html</a> for more info
 *
 * @see <a href="http://legacy.lwjgl.org/index.php.html">LWJGL</a>
 */
public class LWJGLUserInterface implements UserInterface {

    /**
     * The key for the signal to end the program
     */
    private static final int END_PROGRAM_SIGNAL_KEY = Keyboard.KEY_ESCAPE;

    /**
     * Secondary key for ending signal
     */
    private static final int SECONDARY_END_PROGRAM_SIGNAL_KEY = Keyboard.KEY_Q;

    /**
     * Signal key to change color.
     */
    private static final int CHANGE_COLOR_SIGNAL_KEY = Keyboard.KEY_C;

    /**
     * Signal key to go back to default color
     */
    private static final int CHANGE_TO_DEFAULT_COLOR_SIGNAL_KEY = Keyboard.KEY_D;

    /**
     * Default Constructor. It creates the {@link org.lwjgl.input.Keyboard}.
     */
    public LWJGLUserInterface(){
        try {
            Keyboard.create();
        } catch (LWJGLException e) {
            throw new UserInterfaceException(e.getLocalizedMessage());
        }
    }

    /**
     * Checks if the program has been signaled to end.
     *
     * @return true if the program was signal to end. False, otherwise.
     */
    @Override
    public boolean endProgramSignal() {
        return Keyboard.isKeyDown(END_PROGRAM_SIGNAL_KEY) || Keyboard.isKeyDown(SECONDARY_END_PROGRAM_SIGNAL_KEY);
    }

    /**
     * Gets input from the user.
     *
     * @return user input
     */
    @Override
    public String getInput() {
        return String.valueOf(Keyboard.getEventCharacter());
    }

    /**
     * Checks if user wants to change graphics color
     *
     * @return true if user asks to change color, false otherwise
     */
    @Override
    public boolean changeColorSignal() {
        return Keyboard.isKeyDown(CHANGE_COLOR_SIGNAL_KEY);
    }

    /**
     * Checks if user wants to switch colors back to default.
     *
     * @return true if user asks to change color back to default, false otherwise
     */
    @Override
    public boolean changeColorToDefaultSignal() {
        return Keyboard.isKeyDown(CHANGE_TO_DEFAULT_COLOR_SIGNAL_KEY);
    }

    /**
     * Shuts down the user interface.
     */
    @Override
    public void shutdown() {
        Keyboard.destroy();
    }

}
