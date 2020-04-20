package ihm.anthill.sideframe;

import ihm.anthill.mainframe.field.Cell;
import ihm.anthill.mainframe.field.Grid;

import javax.swing.*;
import java.awt.*;

public class ZoomedGridComponent extends JComponent {
    private ZoomedGrid zoomGrid;
    private Grid parent;
    private boolean showGrid;
    private int width;
    private int height;

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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        g.setColor(Color.white);
        g.fillRect(0, 0, this.width * Cell.ZOOM_OF_CELL, this.height * Cell.ZOOM_OF_CELL);
        this.zoomGrid.draw(graphics, this.showGrid);


    }

    public void update() {
        this.repaint();
        revalidate();
    }
}
