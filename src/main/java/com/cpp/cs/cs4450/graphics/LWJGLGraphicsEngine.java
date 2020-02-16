package com.cpp.cs.cs4450.graphics;

import com.cpp.cs.cs4450.models.shapes.DisplayShape;
import com.cpp.cs.cs4450.util.filler.PolygonFiller;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is and implementation of the {@link com.cpp.cs.cs4450.graphics.GraphicsEngine} interface that uses the
 * Lightweight Java Game Library to render the computer graphics. See <a href="http://legacy.lwjgl.org/index.php.html">http://legacy.lwjgl.org/index.php.html</a>
 * for more info.
 *
 * @see <a href="http://legacy.lwjgl.org/index.php.html">LWJGL</a>
 */
public final class LWJGLGraphicsEngine extends AbstractGraphicsEngine implements GraphicsEngine {

    /**
     * Default display window title.
     */
    private static final String DEFAULT_TITLE = "LWJGL Computer Graphics Program";

    /**
     * This background color for the display.
     */
    private static final Color INIT_COLOR = Color.BLACK;

    /**
     * The {@link org.lwjgl.opengl.DisplayMode} to be used by the engine
     */
    private final DisplayMode displayMode;

    private final Map<DisplayShape, Color> originalDisplayColors = new HashMap<>();

    /**
     * Constructor
     *
     * @param displayMode The {@link org.lwjgl.opengl.DisplayMode} to be used by the engine
     */
    public LWJGLGraphicsEngine(final DisplayMode displayMode){
        this(new ArrayList<>(), displayMode);
    }

    /**
     *
     * @param renders List of {@link com.cpp.cs.cs4450.graphics.Renderable} objects to render;
     * @param displayMode The {@link org.lwjgl.opengl.DisplayMode} to be used by the engine
     */
    public LWJGLGraphicsEngine(final List<Renderable> renders, final DisplayMode displayMode){
        this(renders, displayMode, DEFAULT_TITLE);
    }

    /**
     * Constructor
     *
     * @param renders List of {@link com.cpp.cs.cs4450.graphics.Renderable} objects to render;
     * @param displayMode The {@link org.lwjgl.opengl.DisplayMode} to be used by the engine
     * @param title title of the window
     */
    public LWJGLGraphicsEngine(final List<Renderable> renders, final DisplayMode displayMode, final String title){
        super(renders);
        this.displayMode = displayMode;
        initDisplay(title);
        initGL11();
        transform();
        for(final Renderable render : this.renders){
            if(render instanceof DisplayShape){
                final DisplayShape displayShape = (DisplayShape) render;

                originalDisplayColors.put(displayShape, displayShape.getColor());
            }
        }
    }

    /**
     * This method overloads the {@link AbstractGraphicsEngine#render()} method to render objects onto the display.
     * It first clears the display, then renders all the renderable objects. It will then update the {@link org.lwjgl.opengl.Display}
     * being used.
     */
    @Override
    public void render(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        for(final Renderable renderable : renders){
            if(renderable instanceof Fillable) {
                PolygonFiller.fill((Fillable) renderable);
            }

            renderable.render();
        }
        Display.update();
    }

    /**
     * Destroys the display by calling the {@link Display#destroy()} method.
     */
    @Override
    public void shutdown(){
        Display.destroy();
    }

    /**
     * Method that changes graphics colors
     *
     * @param colors colors to change to
     */
    @Override
    public void changeColors(final Map<DisplayShape, Color> colors) {
        final Map<DisplayShape, Color> map = (colors == null) ? originalDisplayColors : colors;
        for(final Renderable render : renders){
            if(render instanceof DisplayShape){
                final DisplayShape displayShape = (DisplayShape) render;
                if(map.containsKey(displayShape)){
                    displayShape.setColor(map.get(displayShape));
                }
            }
        }
    }

    private void transform(){
        for(final Renderable renderable : renders) {
            if (renderable instanceof Transformable) {
                ((Transformable) renderable).transform();
            }
        }
    }


    /**
     * Creates the display the display by calling ({@link Display#create()} method.
     */
    private void initDisplay(final String title){
        try {
            Display.setDisplayMode(displayMode);
            if(title != null && !title.isEmpty()){
                Display.setTitle(title);
            }

            Display.create();
        } catch (LWJGLException e) {
            throw new GraphicsException(e.getLocalizedMessage());
        }
    }

    /**
     * Initializes the LWJGL library.
     */
    private void initGL11(){
        final double left = (displayMode.getWidth() / -2.0);
        final double right = (displayMode.getWidth() / 2.0);
        final double bottom = (displayMode.getHeight() / -2.0);
        final double top = (displayMode.getHeight() / 2.0);

        GL11.glClearColor(INIT_COLOR.getRed(), INIT_COLOR.getGreen(), INIT_COLOR.getBlue(), INIT_COLOR.getAlpha());
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(left, right , bottom, top, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
    }

}