package ihm.anthill;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class ZoomedGridComponent extends JComponent {
    private ZoomedGrid grid;
    private int width;
    private int height;
    private boolean showGrid;


    public ZoomedGridComponent(int width, int height, int mouseX, int mouseY, Grid grid) {
        this.grid = new ZoomedGrid(width, height, mouseX, mouseY, grid);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(width*Cell.ZOOM_OF_CELL, height*Cell.ZOOM_OF_CELL));
        this.width = width;
        this.height = height;
        this.showGrid = true;
        this.setOpaque(true);
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        this.grid.draw(graphics, this.showGrid);
    }


    public static void main (String [] args) {
        JFrame frame = new JFrame("Test Loupe");
        GridComponent gc = new GridComponent(20,20);
        gc.generation(10,10,50);
        Grid grid = gc.getGrid();
        ZoomedGridComponent zgc = new ZoomedGridComponent(11,11,1,1,grid);
        frame.add(zgc);
        frame.pack() ;
        frame.setVisible(true);
    }
}
