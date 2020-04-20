package ihm.anthill.sideframe;

import ihm.anthill.mainframe.field.Grid;

import javax.swing.*;
import java.awt.*;

public class ZoomedFrame extends JFrame {
    private static final int WIDTH = 11;
    private static final int HEIGHT = 11;
    private ZoomedGridComponent zoom;

    public ZoomedFrame(String title, int mouseX, int mouseY, Grid grid, boolean showGrid) {
        super(title);
        this.zoom = new ZoomedGridComponent(11, 11, mouseX, mouseY, grid, showGrid);
        this.add(this.zoom);
        this.setBackground(Color.WHITE);
        this.pack();
        this.setVisible(true);
    }

    public ZoomedGridComponent getZoom() {
        return zoom;
    }
}
