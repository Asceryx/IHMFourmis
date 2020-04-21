package ihm.anthill.sideframe;

import ihm.anthill.mainframe.field.Cell;
import ihm.anthill.mainframe.field.Grid;

import javax.swing.*;
import java.awt.*;

/**
 * <b>paintComponent of zoomedGrid.</b>
 * <p>
 * it is graphic representation of ZoomedGrid.
 * </p>
 *
 * @author Julien Hayrault
 * @version 1.0
 * @see ZoomedGrid
 */
public class ZoomedGridComponent extends JComponent {
    /**
     * Zoomed grid object.
     */
    private ZoomedGrid zoomGrid;
    /**
     * Boolean of hide/show black grid.
     */
    private boolean showGrid;
    /**
     * Width of ZoomedGridComponent
     */
    private int width;
    /**
     * Height of ZoomedGridComponent.
     */
    private int height;

    /**
     * Constructor of ZoomedGridComponent.
     *
     * @param width    Width of ZoomedGridComponent.
     * @param height   Height of ZoomedGridComponent
     * @param mouseX   X Mouse click coordinates.
     * @param mouseY   Y Mouse click coordinates.
     * @param grid     Current grid on which we zoom
     * @param showGrid Show/Hide grid black border.
     */
    public ZoomedGridComponent(int width, int height, int mouseX, int mouseY, Grid grid, boolean showGrid) {
        this.zoomGrid = new ZoomedGrid(width, height, mouseX, mouseY, grid);
        this.showGrid = showGrid;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // Specification : 330x330 px, 11x11 Cell
        // ZOOM_OF_CELL: 30 * 11 Cell = 330
        this.setPreferredSize(new Dimension(width * Cell.ZOOM_OF_CELL, height * Cell.ZOOM_OF_CELL));
        this.setOpaque(true);

        this.width = width;
        this.height = height;
    }

    /**
     * Paint on a graphics.
     *
     * @param g Graphics.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        //Graphics keep in memory the last draw we do, so we draw above it to erase this draw.
        g.setColor(Color.white);
        g.fillRect(0, 0, this.width * Cell.ZOOM_OF_CELL, this.height * Cell.ZOOM_OF_CELL);
        this.zoomGrid.draw(graphics, this.showGrid);


    }

    /**
     * Update this Zoom Grid.
     */
    public void update() {
        this.repaint();
        revalidate();
    }
}
