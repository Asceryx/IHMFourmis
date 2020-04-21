package ihm.anthill.sideframe;

import ihm.anthill.mainframe.field.Cell;
import ihm.anthill.mainframe.field.Grid;

import java.awt.*;

/**
 * <b>Extends of grid. Grid that draw zoomed cell.</b>
 * <p>
 * This grid contains zoomed cell.
 * </p>
 *
 * @author Julien Hayrault
 * @version 1.0
 * @see Grid
 */
public class ZoomedGrid extends Grid {
    /**
     * width of grid.
     */
    private int width;
    /**
     * height of grid.
     */
    private int height;
    /**
     * X Mouse click coordinates.
     */
    private int mouseX;
    /**
     * Y Mouse click coordinates.
     */
    private int mouseY;
    /**
     * Current grid on which we zoom.
     */
    private Grid parent;

    /**
     * Constructor of ZoomedGrid.
     * @param width
     *      width of grid.
     * @param height
     *      height of grid.
     * @param x
     *      x position area to zoom.
     * @param y
     *      y position area to zoom.
     * @param grid
     *      Current grid on which we zoom.
     */
    public ZoomedGrid(int width, int height, int x, int y, Grid grid) {
        super(width, height);
        this.mouseX = x;
        this.mouseY = y;
        this.parent = grid;
        this.width = width;
        this.height = height;

    }

    /**
     * A method to return a rectangular area size of 11*11.
     * If the space to create this area is not enough (for example if we select a point located in one of the ends
     * of the anthill, we translate our display area so that it stays in our anthill.
     * @return
     *      A array summarizing the points of the rectangular area of ​​the zoom.
     */
    public int[] area() {
        int areaX = this.mouseX - (this.width / 2) - this.width % 2;
        int areaY = this.mouseY - (this.height / 2) - this.height % 2;
        int endAreaX = this.mouseX + (this.width / 2);
        int endAreaY = this.mouseY + (this.height / 2);

        if (areaX < 0) {
            endAreaX += Math.abs(areaX);
            areaX = 0;
        }
        if (areaY < 0) {
            endAreaY += Math.abs(areaY);
            areaY = 0;
        }

        if (endAreaX > this.parent.getLargeur()) {
            areaX -= (endAreaX - this.parent.getLargeur());
            endAreaX = this.parent.getLargeur();
        }

        if (endAreaY > this.parent.getHauteur()) {
            areaY -= (endAreaY - this.parent.getHauteur());
            endAreaY = this.parent.getHauteur();
        }


        return new int[]{areaX, areaY, endAreaX, endAreaY};
    }

    /**
     * Draw the anthill.
     *
     * @param panelDraw Support where the grid to be drawing.
     * @param show      show/hide black border.
     */
    @Override
    public void draw(Graphics2D panelDraw, boolean show) {
        int[] area = area();
        for (int i = area[0]; i < area[2]; i++) {
            for (int j = area[1]; j < area[3]; j++) {
                Cell c = this.parent.getCell(i, j);
                c.setBorder(show);
                c.drawZoomCell(panelDraw, area[0], area[1]);
            }
        }
    }
}
