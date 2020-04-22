package jeuDesFourmis.ihm.anthill.mainframe.ground;

import java.awt.*;

/**
 * <b>Representation of a wall in anthill.</b>
 * <p>
 * A wall is an obstacle for a ant. If a cell is a wall, there are not ant or seed in this cell
 * and a ant cannot be in a wall.
 * </p>
 *
 * @author Julien Hayrault
 * @version 1.0
 * @see Cell
 */
public class Wall extends Cell {

    /**
     * Constructor of wall.
     *
     * @param x line coordinates.
     * @param y column coordinates.
     */
    public Wall(int x, int y) {
        super(x, y);
    }

    /**
     * A Wall is never empty.
     *
     * @return false
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Paint a <b>low</b> detailed wall. (black rectangle)
     *
     * @param grid Graphic where paint.
     */
    @Override
    public void drawCell(Graphics2D grid) {
        super.drawCell(grid);
        grid.drawRect(this.x * SIZE_OF_CELL, this.y * SIZE_OF_CELL, SIZE_OF_CELL, SIZE_OF_CELL);
        grid.setColor(new Color(0, 0, 0));
        grid.fillRect(this.x * SIZE_OF_CELL, this.y * SIZE_OF_CELL, SIZE_OF_CELL, SIZE_OF_CELL);
    }

    /**
     * Paint a <b>high</b> detailed wall. (detailed wall)
     *
     * @param grid       Graphic where paint.
     * @param translateX X translation. When defining a point with a non-zero coordinate as origin,
     *                   the origin must be offset so that the drawing begins at coordinates 0 0.
     * @param translateY Y translation. When defining a point with a non-zero coordinate as origin,
     */
    @Override
    public void drawZoomCell(Graphics2D grid, int translateX, int translateY) {
        super.drawZoomCell(grid, translateX, translateY);
        //Origin
        int Ox = (this.x - translateX) * ZOOM_OF_CELL;
        int Oy = (this.y - translateY) * ZOOM_OF_CELL;
        //End of cell.
        int OxMax = Ox + ZOOM_OF_CELL;
        grid.setColor(new Color(0, 0, 0));


        int dx;
        int dy;
        int offset;

        for (int i = 0; i * ZOOM_OF_CELL / 6 <= ZOOM_OF_CELL; i++) {
            dy = i * ZOOM_OF_CELL / 6;
            grid.drawLine(Ox, Oy + dy, OxMax, Oy + dy);
        }

        for (int z = 0; z * ZOOM_OF_CELL / 6 < ZOOM_OF_CELL; z++) {
            offset = z * -ZOOM_OF_CELL / 6;
            for (int j = 1; offset + j * ZOOM_OF_CELL / 3 <= ZOOM_OF_CELL ; j++) {
                dx = offset + j * ZOOM_OF_CELL / 3;
                if (offset + j * ZOOM_OF_CELL / 3 > 0) {
                    grid.drawLine(Ox + dx, Oy + z * ZOOM_OF_CELL / 6, Ox + dx, Oy + (z + 1) * ZOOM_OF_CELL / 6);
                }
            }
        }
    }
}
