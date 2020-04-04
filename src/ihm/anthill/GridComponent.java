package ihm.anthill;


import ihm.settings.Deactivable;
import model.Fourmiliere;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

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
     *     Caution : real size of the grid is :
     *     (width * sizeofCell) X (height * sizeofCellà
     * </p>
     *
     * @see Cell
     */
    private int width;

    /**
     * Height of a grid.
     * <p>
     *     Caution : real size of the grid is :
     *     (width * sizeofCell) X (height * sizeofCellà
     * </p>
     *
     * @see Cell
     */
    private int height;

    /**
     * Define if the grid must be show/hide
     * <p>
     *     Caution : real size of the grid is :
     *     (width * sizeofCell) X (height * sizeofCellà
     * </p>
     *
     * @see GridComponent#setShowGrid(boolean)
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

    private boolean activate;

    /**
     * Constructor of GridComponent
     * <p>
     *     When the object is constructed, the size is suitable to the content to be displayed.
     *     The grid is displayed by default. We initialize the different listener.
     * </p>
     * @param width
     * @param height
     */
    public GridComponent(int width, int height) {
        // Size adapted with the number of cell
        // width : number of cell in width
        // height : number of cell in height
        // Real size : number of cell * size of ONE cell
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(width*Cell.SIZE_OF_CELL, height*Cell.SIZE_OF_CELL));
        // Creation of a blank grid (no ants, no seeds, no walls)
        this.grid = new Grid(width, height);
        this.width = width;
        this.height = height;
        this.showGrid = true;
        this.activate = true;
        this.eventKey = new boolean[2];


        // Listener on the different components (Keyboard, Mouse, Wheel)
        addKeyListener (this);
        addMouseListener(this);
        addMouseWheelListener (this) ;

        // Allows the display of component
        this.setOpaque(true);

        // /!\ Important /!\ : Have focus otherwise no listening on the keyboard
        // Personal note: you must avoid the focus on the buttons (setFocusable(false))
        this.setFocusable(true);
    }

    /**
     * Show/Hide the grid on component.
     * @param show
     *      Define if grid must be show or not.
     *
     */
    public void setShowGrid(boolean show){
        this.showGrid = show;
        repaint();
    }

    @Override
    public void setHide(){
        this.showGrid = false;
        repaint();
    }

    @Override
    public void setShow(){
        this.showGrid = true;
        repaint();
    }

    /**
     * Reset the component.
     */
    public void reset(){
        this.grid = new Grid(this.width, this.height);
        repaint();
    }

    public void resize(int size){
        this.width = size;
        this.height = size;
        this.reset();
    }

    public int getGridWidth(){
        return this.width;
    }

    public int getGridHeigth(){
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
     * @param probSeed
     *      Probability to spawn seeds
     * @param probAnt
     *      Probability to spawn ants
     * @param probWall
     *      Probability to spawn walls
     */
    public void generation(int probSeed, int probAnt,  int probWall) {
        this.grid.generate(probSeed, probAnt,  probWall);
        repaint();
    }


    /**
     *
     * @return grid that represent anthill.
     */
    public Grid getGrid(){
        return this.grid;
    }

    /**
     * Draw a graphical anthill.
     * @param g
     */
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        this.grid.draw(graphics, this.showGrid);
        this.setPreferredSize(new Dimension(width*Cell.SIZE_OF_CELL, height*Cell.SIZE_OF_CELL));
        Window window = SwingUtilities.windowForComponent(this);
        window.pack();
        this.revalidate();
    }

    /**
     * Mouse event on grid.
     * <p>
     *     <ul>
     *         <li>If only click : create a wall. </li>
     *         <li>If click + shift : create a ant. </li>
     *     </ul>
     * </p>
     * @param e
     *      Event of mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        this.eventKey[0] = true;
        int mousex = e.getX();
        int mousey = e.getY();

        int i = mousex / Cell.SIZE_OF_CELL;
        int j = mousey / Cell.SIZE_OF_CELL;
        System.out.println(i + " ; " + j);


        if (this.eventKey[0] && this.eventKey[1] && this.activate)
        {
            System.out.println("Mouse clicked + Shift pressed");
            this.grid.ajouteFourmi(i,j);
        }
        else if (this.eventKey[0] && this.activate)
        {
            System.out.println("Mouse clicked");
            boolean bool = this.grid.getMur(i,j);
            this.grid.setMur(i,j,!bool);
        }
         repaint();
    }

    /**
     * Mouse release event, deactivate enabling mouse click
     * @param e
     *      Event of mouse
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        this.eventKey[0] = false;
    }


    /**
     * Listening if Shift are pressed.
     *
     * @param e
     *      Keyboard event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Shift pressed");
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            this.eventKey[1] =true;
        }
    }

    /**
     * Release the key. Disabled key event.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        this.eventKey[1] = false;
    }

    /**
     * Listening if mouse wheel move and count number of rotation.
     * Each rotation place some seed.
     * @param e
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (this.activate){
            int rotation = e.getWheelRotation () ;
            System.out.println(e.getX()/Cell.SIZE_OF_CELL + ";"+ e.getY()/Cell.SIZE_OF_CELL);
            int i = e.getX()/Cell.SIZE_OF_CELL;
            int j = e.getY()/Cell.SIZE_OF_CELL;
            Cell cell = this.grid.getCell(i,j);
            int seed = this.grid.getQteGraines(i,j) + (-rotation);
            if (seed < Fourmiliere.QMAX && seed >= 0 && !(cell instanceof Wall))
            {
                this.grid.setQteGraines(i,j,seed);
            }
            else if (seed >= Fourmiliere.QMAX && !(cell instanceof Wall)) {
                this.grid.setQteGraines(i,j,4);
            }
            else if (seed < 0 && !(cell instanceof Wall)) {
                this.grid.setQteGraines(i,j,0);
            }
            repaint();
        }
    }

    /**
     * Update grid and repaint graphics.
     */
    public void update(){
        this.grid.update();
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void activate() {
        this.activate = true;
    }

    @Override
    public void deactivate() {
        this.activate = false;
    }
}
