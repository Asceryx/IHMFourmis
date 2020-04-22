package jeuDesFourmis.ihm.anthill.mainframe.ground;
import java.awt.*;

/**
 * <b>Abstract class of cell. Represent a atomic element of grid.</b>
 * A cell is defined :
 * <ul>
 *     <li>a coordinate</li>
 *     <li>whether it has a border or not</li>
 * </ul>
 *
 * @author Julien Hayrault
 * @version 1.0
 * @see Grid
 * @see Case
 * @see Wall
 */
public abstract class Cell {
    /**
     * Constant size of cell. Specification define a 100*100 cell, with 5 pixels size.
     */
    public static final int SIZE_OF_CELL = 5;
    /**
     * Constant size of a zoom cell. Specification define a 11*11 cell, in 330*330 px frame. So 30 for 1 cell.
     */
    public static final int ZOOM_OF_CELL = 30;
    /**
     * line coordinate.
     */
    protected int x;
    /**
     * column coordinate.
     */
    protected int y;
    /**
     * Define if the border is hide or not.
     */
    protected boolean border;

    /**
     * Constructor of cell.
     *
     * @param x line coordinate.
     * @param y column coordinate.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.border = true;
    }

    /**
     * Constructor of cell.
     *
     * @param x      line coordinate.
     * @param y      column coordinate.
     * @param border show borders.
     */
    public Cell(int x, int y, boolean border) {
        this.x = x;
        this.y = y;
        this.border = border;
    }

    /**
     * Getting if border is show.
     *
     * @return boolean.
     */
    public boolean showBorder() {
        return this.border;
    }

    /**
     * Setting if a border is show.
     *
     * @param show boolean if show or not.
     */
    public void setBorder(boolean show) {
        this.border = show;
    }

    /**
     * Define if Cell is empty.
     *
     * @return boolean.
     */
    public abstract boolean isEmpty();

    /**
     * Drawing a cell.
     *
     * @param grid Graphic where drawing.
     */
    public void drawCell(Graphics2D grid) {
        if (this.showBorder()) {
            grid.setColor(new Color(0, 0, 0));
            grid.drawRect(this.x * SIZE_OF_CELL, this.y * SIZE_OF_CELL, SIZE_OF_CELL, SIZE_OF_CELL);
        }
        grid.setColor(new Color(255, 255, 255));
    }

    /**
     * @param grid       Graphic where paint.
     * @param translateX X translation. When defining a point with a non-zero coordinate as origin,
     *                   the origin must be offset so that the drawing begins at coordinates 0 0.
     * @param translateY Y translation. When defining a point with a non-zero coordinate as origin,
     *                   the origin must be offset so that the drawing begins at coordinates 0 0.
     */
    public void drawZoomCell(Graphics2D grid, int translateX, int translateY) {
        if (this.showBorder()) {
            grid.setColor(new Color(0, 0, 0));
            grid.drawRect((this.x - translateX) * ZOOM_OF_CELL, (this.y - translateY) * ZOOM_OF_CELL, ZOOM_OF_CELL, ZOOM_OF_CELL);
        }
        grid.setColor(new Color(255, 255, 255));
    }
}