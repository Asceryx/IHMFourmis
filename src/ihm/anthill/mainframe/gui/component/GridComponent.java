package ihm.anthill.mainframe.gui.component;


import ihm.anthill.mainframe.field.Cell;
import ihm.anthill.mainframe.field.Grid;
import ihm.anthill.mainframe.field.Wall;
import ihm.anthill.mainframe.gui.Hidden;
import ihm.anthill.sideframe.ZoomedFrame;
import ihm.anthill.sideframe.ZoomedGridComponent;
import ihm.settings.Deactivable;
import model.Fourmiliere;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <b>Class representing a graphical component for anthill grid.</b>
 * <p>
 *     Based on anthill grid.
 *     This component can be add to a Panel.
 * </p>
 * @see Grid
 *
 * @author Julien Hayrault
 * @version 1.0
 */
public class GridComponent extends JComponent implements MouseListener, KeyListener, MouseWheelListener, Hidden, Deactivable {
    /**
     * Grid which representing the anthill.
     *
     * @see Grid
     */
    private Grid grid;

    /**
     * Width of a grid.
     * <p>
     * Caution : real size of the grid is :
     * (width * sizeofCell) X (height * sizeofCellà
     * </p>
     *
     * @see Cell
     */
    private int width;

    /**
     * Height of a grid.
     * <p>
     * Caution : real size of the grid is :
     * (width * sizeofCell) X (height * sizeofCellà
     * </p>
     *
     * @see Cell
     */
    private int height;

    /**
     * Define if the grid must be show/hide
     * <p>
     * Caution : real size of the grid is :
     * (width * sizeofCell) X (height * sizeofCellà
     * </p>
     *
     * @see GridComponent
     */
    private boolean showGrid;

    /**
     * Summarizes the status of keyboard / mouse / wheel states.
     * <p>
     *     <ul>
     *         <li> Mouse click : eventKey[0] </li>
     *         <li> Keyboard shift : eventKey[1]    </li>
     *     </ul>
     *
     * </p>
     */
    private boolean[] eventKey;

    /**
     * Define if a grid interaction is possible.
     */
    private boolean activate;
    /**
     * Define if zoom is activate.
     */
    private boolean zoom;
    /**
     * Zoom component.
     */
    private ZoomedGridComponent zoomcomponent;
    /**
     * Define if resize is available (initialization : false)
     */
    private boolean resize;

    /**
     * Constructor of GridComponent
     * <p>
     * When the object is constructed, the size is suitable to the content to be displayed.
     * The grid is displayed by default. We initialize the different listener.
     * </p>
     *
     * @param width  Number of cell in width
     * @param height Number of cell in height
     */
    public GridComponent(int width, int height) {
        // Size adapted with the number of cell
        // width : number of cell in width
        // height : number of cell in height
        // Real size : number of cell * size of ONE cell
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(width * Cell.SIZE_OF_CELL, height * Cell.SIZE_OF_CELL));
        // Creation of a blank grid (no ants, no seeds, no walls)
        this.grid = new Grid(width, height);
        this.width = width;
        this.height = height;
        this.showGrid = true;
        this.activate = true;
        this.resize = false;
        this.zoom = false;
        this.eventKey = new boolean[2];


        // Listener on the different components (Keyboard, Mouse, Wheel)
        addKeyListener(this);
        addMouseListener(this);
        addMouseWheelListener(this);

        // Allows the display of component
        this.setOpaque(true);

        // /!\ Important /!\ : Have focus otherwise no listening on the keyboard
        // Personal note: you must avoid the focus on the buttons (setFocusable(false))
        this.setFocusable(true);
    }

    /**
     * Hide the grid on component.
     */
    @Override
    public void setHide() {
        this.showGrid = false;
        repaint();
    }

    /**
     * Show the grid on component.
     */
    @Override
    public void setShow() {
        this.showGrid = true;
        repaint();
    }

    /**
     * Reset the component.
     */
    public void reset() {
        this.grid = new Grid(this.width, this.height);

        repaint();
    }

    /**
     * Resize the component.
     *
     * @param size Size of component.
     */
    public void resize(int size) {
        this.width = size;
        this.height = size;
        this.resize = true;
        this.reset();
    }

    /**
     * Get width of grid.
     *
     * @return width of grid.
     */
    public int getGridWidth() {
        return this.width;
    }

    /**
     * Get height of grid.
     *
     * @return height of grid.
     */
    public int getGridHeight() {
        return this.height;
    }

    /**
     * Generate a random content, with some probabilities for each item.
     * <p>
     *     <ul>
     *         <li>probSeed means there is a 1 in probSeed chance of seed spawning.</li>
     *         <li>probAnt means there is a 1 in probAnt chance of ant spawning.</li>
     *         <li>probWall means there is a 1 in probWall chance of wall spawning.</li>
     *     </ul>
     * </p>
     *
     * @param probSeed Probability to spawn seeds
     * @param probAnt  Probability to spawn ants
     * @param probWall Probability to spawn walls
     */
    public void generation(int probSeed, int probAnt, int probWall) {
        this.grid.generate(probSeed, probAnt, probWall);
        repaint();
    }


    /**
     * @return grid that represent anthill.
     */
    public Grid getGrid() {
        return this.grid;
    }

    /**
     * Draw a graphical anthill.
     *
     * @param g Graphic component
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        this.grid.draw(graphics, this.showGrid);
        this.setPreferredSize(new Dimension(width * Cell.SIZE_OF_CELL, height * Cell.SIZE_OF_CELL));
        this.revalidate();
        if (this.resize) {
            Window window = SwingUtilities.windowForComponent(this);
            window.pack();
            this.resize = false;
        }

    }

    /**
     * Mouse event on grid.
     * <p>
     *     <ul>
     *         <li>If only click : create a wall. </li>
     *         <li>If zoom button push and click : Zoom</li>
     *         <li>If click + shift : create a ant. </li>
     *     </ul>
     * </p>
     *
     * @param e Event of mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        this.eventKey[0] = true;
        int mousex = e.getX();
        int mousey = e.getY();

        int i = mousex / Cell.SIZE_OF_CELL;
        int j = mousey / Cell.SIZE_OF_CELL;


        if (this.eventKey[0] && this.eventKey[1] && this.activate) {
            this.grid.ajouteFourmi(i, j);
        } else if (this.eventKey[0] && this.zoom) {
            String title = "Zoom en " + i + " : " + j;
            ZoomedFrame z = new ZoomedFrame(title, i, j, this.grid, this.showGrid);
            z.setLocationRelativeTo(z.getParent());
            zoomcomponent = z.getZoom();
        } else if (this.eventKey[0] && this.activate) {
            boolean bool = this.grid.getMur(i, j);
            this.grid.setMur(i, j, !bool);
        }

        repaint();
    }

    /**
     * Mouse release event, deactivate enabling mouse click
     *
     * @param e Event of mouse
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        this.eventKey[0] = false;
    }


    /**
     * Listening if Shift are pressed.
     *
     * @param e Keyboard event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Shift pressed");
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            this.eventKey[1] = true;
        }
    }

    /**
     * Release the key. Disabled key event.
     *
     * @param e Keyevent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        this.eventKey[1] = false;
    }

    /**
     * Listening if mouse wheel move and count number of rotation.
     * Each rotation place some seed.
     *
     * @param e Keyevent
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (this.activate) {
            int rotation = e.getWheelRotation();
            System.out.println(e.getX() / Cell.SIZE_OF_CELL + ";" + e.getY() / Cell.SIZE_OF_CELL);
            int i = e.getX() / Cell.SIZE_OF_CELL;
            int j = e.getY() / Cell.SIZE_OF_CELL;
            Cell cell = this.grid.getCell(i, j);
            int seed = this.grid.getQteGraines(i, j) + (-rotation);
            if (seed < Fourmiliere.QMAX && seed >= 0 && !(cell instanceof Wall)) {
                this.grid.setQteGraines(i, j, seed);
            } else if (seed >= Fourmiliere.QMAX && !(cell instanceof Wall)) {
                this.grid.setQteGraines(i, j, 4);
            } else if (seed < 0 && !(cell instanceof Wall)) {
                this.grid.setQteGraines(i, j, 0);
            }
            repaint();
        }
    }

    /**
     * Update grid and repaint graphics.
     */
    public void update() {
        this.grid.update();
        repaint();
        revalidate();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Activate grid interaction (mouse, keyboard...)
     */
    @Override
    public void activate() {
        this.activate = true;
    }

    /**
     * Deactivate grid interaction (mouse, keyboard...)
     */
    @Override
    public void deactivate() {
        this.activate = false;
    }

    /**
     * Activate zoom.
     */
    public void zoomActivate() {
        this.zoom = true;
    }

    /**
     * Deactivate zoom.
     */
    public void zoomDeactivate() {
        this.zoom = false;
    }

    /**
     * Get if zoom is activate/deactivate.
     *
     * @return boolean
     */
    public boolean isZoom() {
        return this.zoom;
    }

    /**
     * Get ZoomComponent.
     *
     * @return ZoomGridComponent
     */
    public ZoomedGridComponent getZoomcomponent() {
        return zoomcomponent;
    }
}
