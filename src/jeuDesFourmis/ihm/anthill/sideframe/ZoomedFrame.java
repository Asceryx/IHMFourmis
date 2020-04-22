package jeuDesFourmis.ihm.anthill.sideframe;

import jeuDesFourmis.ihm.anthill.mainframe.ground.Grid;

import javax.swing.*;
import java.awt.*;

/**
 * <b>Frame which contains a zoom component.</b>
 * <p>
 * This size of frame are fixed by specification (330x330 px).
 * </p>
 *
 * @author Julien Hayrault
 * @version 1.0
 */
public class ZoomedFrame extends JFrame {
    /**
     * WIDTH constant. cells = 30, width = 11 : 11 * 30 = 330px in width
     */
    private static final int WIDTH = 11;
    /**
     * HEIGHT constant. cells = 30, height = 11 : 11 * 30 = 330px in height
     */
    private static final int HEIGHT = 11;
    /**
     * Zoom Grid component.
     */
    private ZoomedGridComponent zoom;

    /**
     * Constructor of ZoomedFrame.
     *
     * @param title    Title of frame.
     * @param mouseX   X Mouse click coordinates.
     * @param mouseY   Y Mouse click coordinates.
     * @param grid     Current grid on which we zoom.
     * @param showGrid Show/Hide grid black border.
     */
    public ZoomedFrame(String title, int mouseX, int mouseY, Grid grid, boolean showGrid) {
        super(title);
        this.zoom = new ZoomedGridComponent(WIDTH, HEIGHT, mouseX, mouseY, grid, showGrid);
        this.add(this.zoom);
        this.setBackground(Color.WHITE);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Get zoom grid component.
     *
     * @return ZoomedGridComponent
     */
    public ZoomedGridComponent getZoom() {
        return zoom;
    }
}
