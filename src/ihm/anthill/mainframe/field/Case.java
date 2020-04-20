package ihm.anthill.mainframe.field;

import model.Fourmi;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Case is the graphical representation of a space</b>
 * <p>
 * A case is an object that can be :
 *     <ul>
 *         <li>an empty space</li>
 *         <li>a space occupied by an ant</li>
 *         <li>a space occupied by an ant carrying a seed</li>
 *         <li>a space occupied by one or more seeds</li>
 *     </ul>
 *     A case can be seen in a basic way, or if we zoom in, in detail.
 * </p>
 *
 * @author Julien Hayrault
 * @version 1.0
 * @see Cell
 * @see ihm.anthill.sideframe.ZoomedGridComponent
 * @see ihm.anthill.mainframe.gui.component.GridComponent
 */
public class Case extends Cell {
    /**
     * The ant present in the case. This value is null if no ant.
     *
     * @see Fourmi
     */
    private Fourmi containAnt;
    /**
     * The number of seeds in the case.
     */
    private int containSeed;
    /**
     * The list of seeds present in the case.
     *
     * @see GraphicSeed
     */
    private List<GraphicSeed> listSeed;

    /**
     * Constructor of Case.
     *
     * @param x Position in line of case.
     * @param y Position in column of case.
     */
    public Case(int x, int y) {
        super(x, y);
        this.containAnt = null;
        this.containSeed = 0;
        this.listSeed = new ArrayList<>();
    }

    /**
     * Constructor of Case.
     *
     * @param x           Position in line of case.
     * @param y           Position in column of case.
     * @param containAnt  The ant present in this case.
     * @param containSeed The seed (s) quantity of seeds present in this case.
     */
    public Case(int x, int y, Fourmi containAnt, int containSeed) {
        super(x, y);
        this.containAnt = containAnt;
        this.containSeed = containSeed;
        this.listSeed = new ArrayList<>();
        for (int i = 0; i < containSeed; i++) {
            this.listSeed.add(new GraphicSeed());
        }
    }

    /**
     * Presence of an ant in a case.
     *
     * @return boolean if case contains ants
     */
    public boolean isContainAnt() {
        return !(this.containAnt == null);
    }

    /**
     * Presence of an seed in a case.
     *
     * @return boolean if case contains seed(s).
     */
    public boolean isContainSeed() {
        return !(this.containSeed == 0);
    }

    /**
     * Tells if a case is empty (Not ant, not seed).
     *
     * @return boolean if case is empty.
     */
    @Override
    public boolean isEmpty() {
        return !(this.isContainAnt()) && !(this.isContainSeed());
    }

    /**
     * Get ant in a case.
     *
     * @return ant or null.
     */
    public Fourmi getAnt() {
        if (isContainAnt()) {
            return this.containAnt;
        }
        return null;
    }

    /**
     * Set an ant in a case.
     *
     * @param ant
     */
    public void setAnt(Fourmi ant) {
        if (!(isContainAnt())) {
            this.containAnt = ant;
        }
    }

    /**
     * Set seed(s) in a case.
     *
     * @param count number of seed in case.
     */
    public void setSeed(int count) {
        while (count < this.containSeed && this.containSeed > 0) {
            this.listSeed.remove(this.listSeed.size() - 1);
            this.containSeed--;
        }
        while (count > this.containSeed) {
            this.listSeed.add(new GraphicSeed());
            this.containSeed++;
        }
    }

    /**
     * Paint a <b>low</b> detailed ant.
     *
     * @param grid Graphic where paint.
     * @param ant  Ant to paint.
     */
    public void paintAnt(Graphics grid, Fourmi ant) {
        grid.setColor(new Color(255, 255, 255));
        grid.drawOval(this.x * SIZE_OF_CELL + 1, this.y * SIZE_OF_CELL + 1, SIZE_OF_CELL - 2, SIZE_OF_CELL - 2);

        if (ant.porte()) {
            grid.setColor(Color.BLUE);
        } else {
            grid.setColor(Color.GREEN);
        }

        grid.fillOval(this.x * SIZE_OF_CELL + 1, this.y * SIZE_OF_CELL + 1, SIZE_OF_CELL - 2, SIZE_OF_CELL - 2);
    }

    /**
     * Paint a <b>low</b> detailed cell.
     *
     * @param grid Graphic where paint.
     */
    @Override
    public void drawCell(Graphics2D grid) {
        super.drawCell(grid);
        //Drawing seed(s) : rectangle in shade of red.
        if (this.containSeed > 0) {
            grid.setColor(new Color(255, 250 - this.containSeed * 50, 250 - this.containSeed * 50));
        }
        if (!this.showBorder()) {
            grid.drawRect(this.x * SIZE_OF_CELL, this.y * SIZE_OF_CELL, SIZE_OF_CELL, SIZE_OF_CELL);
        }
        grid.fillRect(this.x * SIZE_OF_CELL + 1, this.y * SIZE_OF_CELL + 1, SIZE_OF_CELL - 1, SIZE_OF_CELL - 1);
        //Drawing ant : Circle green or blue.
        if (this.isContainAnt()) {
            paintAnt(grid, this.containAnt);
        }
    }

    /**
     * Paint a <b>high</b> detailed ant.
     *
     * @param grid       Graphic where paint.
     * @param ant        Ant to paint.
     * @param translateX Useful if you zooming, because the origin of the start of the zoom area is not in X = 0.
     * @param translateY Useful if you zooming, because the origin of the start of the zoom area is not in Y = 0.
     */
    public void paintDetailedAnt(Graphics2D grid, Fourmi ant, int translateX, int translateY) {
        grid.setColor(new Color(0, 0, 0));
        // Origin of a cell.
        int Ox = (this.x - translateX) * ZOOM_OF_CELL;
        int Oy = (this.y - translateY) * ZOOM_OF_CELL;

        // Middle of a cell.
        int Mx = Ox + ZOOM_OF_CELL / 2;
        int My = Oy + ZOOM_OF_CELL / 2;

        grid.setColor(Color.BLACK);
        //Drawing of the legs of the ant (x3)
        grid.drawLine(Mx, My, Ox + 3 * ZOOM_OF_CELL / 4, Oy + ZOOM_OF_CELL / 4);
        grid.drawLine(Ox + 3 * ZOOM_OF_CELL / 4, Oy + ZOOM_OF_CELL / 4, Ox + 8 * ZOOM_OF_CELL / 10, Oy + ZOOM_OF_CELL / 4);
        grid.drawLine(Mx, My, Ox + 3 * ZOOM_OF_CELL / 4, Oy + 3 * ZOOM_OF_CELL / 4);
        grid.drawLine(Ox + 3 * ZOOM_OF_CELL / 4, Oy + 3 * ZOOM_OF_CELL / 4, Ox + 8 * ZOOM_OF_CELL / 10, Oy + 3 * ZOOM_OF_CELL / 4);
        grid.drawLine(Mx, My, Ox + 8 * ZOOM_OF_CELL / 10, My);

        //Drawing of the legs and right (x3)
        grid.drawLine(Mx, My, Ox + ZOOM_OF_CELL / 4, Oy + ZOOM_OF_CELL / 4);
        grid.drawLine(Ox + ZOOM_OF_CELL / 4, Oy + ZOOM_OF_CELL / 4, Ox + 2 * ZOOM_OF_CELL / 10, Oy + ZOOM_OF_CELL / 4);
        grid.drawLine(Mx, My, Ox + 2 * ZOOM_OF_CELL / 10, My);
        grid.drawLine(Mx, My, Ox + ZOOM_OF_CELL / 4, Oy + 3 * ZOOM_OF_CELL / 4);
        grid.drawLine(Ox + ZOOM_OF_CELL / 4, Oy + 3 * ZOOM_OF_CELL / 4, Ox + 2 * ZOOM_OF_CELL / 10, Oy + 3 * ZOOM_OF_CELL / 4);

        //Drawing of the ant's left antenna
        grid.drawLine(Mx, Oy + ZOOM_OF_CELL / 4, Ox + 3 * ZOOM_OF_CELL / 4, Oy);
        //Drawing of the ant's right antenna
        grid.drawLine(Mx, Oy + ZOOM_OF_CELL / 4, Ox + ZOOM_OF_CELL / 4, Oy);

        //Scaling of first part of the body of the ant in comparison to to the size of the cell.
        int SIZE_OF_ITEM = (20 * ZOOM_OF_CELL) / 60;
        grid.setColor(Color.GREEN);
        //Drawing the head of ant.
        grid.drawOval(Mx - SIZE_OF_ITEM / 2, Oy + ZOOM_OF_CELL / 4 - SIZE_OF_ITEM / 2, SIZE_OF_ITEM, SIZE_OF_ITEM);
        grid.fillOval(Mx - SIZE_OF_ITEM / 2, Oy + ZOOM_OF_CELL / 4 - SIZE_OF_ITEM / 2, SIZE_OF_ITEM, SIZE_OF_ITEM);
        //Scaling of second part of the body of the ant in comparison to to the size of the cell.
        SIZE_OF_ITEM = (14 * ZOOM_OF_CELL) / 60;
        //Drawing of the first part of the ant's body.
        grid.drawOval(Mx - SIZE_OF_ITEM / 2, Oy + 2 * ZOOM_OF_CELL / 4 - SIZE_OF_ITEM / 2, SIZE_OF_ITEM, SIZE_OF_ITEM);
        grid.fillOval(Mx - SIZE_OF_ITEM / 2, Oy + 2 * ZOOM_OF_CELL / 4 - SIZE_OF_ITEM / 2, SIZE_OF_ITEM, SIZE_OF_ITEM);
        //Scaling of third part of the body of the ant in comparison to to the size of the cell.
        SIZE_OF_ITEM = (20 * ZOOM_OF_CELL) / 60;
        //Drawing of the second part of the ant's body.
        grid.drawOval(Mx - SIZE_OF_ITEM / 2, Oy + 3 * ZOOM_OF_CELL / 4 - SIZE_OF_ITEM / 2, SIZE_OF_ITEM, SIZE_OF_ITEM);
        grid.fillOval(Mx - SIZE_OF_ITEM / 2, Oy + 3 * ZOOM_OF_CELL / 4 - SIZE_OF_ITEM / 2, SIZE_OF_ITEM, SIZE_OF_ITEM);

        //If an ant takes a seed : drawing of a blue circle on the ant.
        if (ant.porte()) {
            grid.setColor(Color.BLUE);
            SIZE_OF_ITEM = ZOOM_OF_CELL / 2;
            grid.drawOval(Mx - SIZE_OF_ITEM / 2, My - SIZE_OF_ITEM / 2, SIZE_OF_ITEM, SIZE_OF_ITEM);
            grid.fillOval(Mx - SIZE_OF_ITEM / 2, My - SIZE_OF_ITEM / 2, SIZE_OF_ITEM, SIZE_OF_ITEM);
        }

        grid.setColor(new Color(0, 0, 0));
    }

    /**
     * Paint a <b>high</b> detailed seed(s).
     *
     * @param grid       Graphic where paint.
     * @param translateX Useful if you zooming, because the origin of the start of the zoom area is not in X = 0.
     * @param translateY Useful if you zooming, because the origin of the start of the zoom area is not in Y = 0.
     */
    public void paintDetailedSeed(Graphics2D grid, int translateX, int translateY) {
        // Origin of a cell.
        int Ox = (this.x - translateX) * ZOOM_OF_CELL;
        int Oy = (this.y - translateY) * ZOOM_OF_CELL;

        // Middle of a cell.
        int Mx = Ox + ZOOM_OF_CELL / 2;
        int My = Oy + ZOOM_OF_CELL / 2;

        grid.setColor(new Color(0, 0, 0));
        //Saves the current configuration of the graphic.
        AffineTransform oldXForm = grid.getTransform();


        for (int i = 0; i < this.containSeed; i++) {
            GraphicSeed current = this.listSeed.get(i);
            //Getting drawing parameters seed.
            int angle = current.getAngle();
            int randX = current.getRandX();
            int randY = current.getRandY();
            // rotate drawing.
            grid.rotate(Math.toRadians(angle), Mx + randX, My + randY);

            // Fill seed in red.
            grid.setColor(new Color(255, 25, 25));
            grid.fillOval(Mx - GraphicSeed.SIZE_OF_SEED / 2 + randX / 2, My - 2 * GraphicSeed.SIZE_OF_SEED / 2 + randY / 2, GraphicSeed.SIZE_OF_SEED, 2 * GraphicSeed.SIZE_OF_SEED);
            grid.setColor(new Color(0, 0, 0));
            // Draw the edges.
            grid.drawOval(Mx - GraphicSeed.SIZE_OF_SEED / 2 + randX / 2, My - 2 * GraphicSeed.SIZE_OF_SEED / 2 + randY / 2, GraphicSeed.SIZE_OF_SEED, 2 * GraphicSeed.SIZE_OF_SEED);
            grid.setColor(new Color(255, 255, 255));
            grid.setTransform(oldXForm);
        }
        grid.setStroke(new BasicStroke(1));
        // Returns to previous configuration.
        grid.setTransform(oldXForm);
    }

    /**
     * Paint a <b>high</b> detailed cell.
     *
     * @param grid       Graphic where paint.
     * @param translateX Useful if you zooming, because the origin of the start of the zoom area is not in X = 0.
     * @param translateY Useful if you zooming, because the origin of the start of the zoom area is not in Y = 0.
     */
    @Override
    public void drawZoomCell(Graphics2D grid, int translateX, int translateY) {
        super.drawZoomCell(grid, translateX, translateY);
        if (this.containSeed > 0) {
            paintDetailedSeed(grid, translateX, translateY);
        }
        if (!this.showBorder()) {
            grid.drawRect((this.x - translateX) * ZOOM_OF_CELL, (this.y - translateY) * ZOOM_OF_CELL, ZOOM_OF_CELL, ZOOM_OF_CELL);
        }

        if (this.isContainAnt()) {
            paintDetailedAnt(grid, this.containAnt, translateX, translateY);
        }
    }

    /**
     * <b>the GraphicSeed class summarizes all the parameters that a seed must take.</b>
     * <p>
     * A GraphicSeed contains the following graphic information :
     *    <ul>
     *        <li>a size compared to the size of the cell</li>
     *        <li>a random orientation (in degrees)</li>
     *        <li>a random x position in cell</li>
     *        <li>a random y position in cell</li>
     *    </ul>
     * </p>
     */
    public class GraphicSeed {
        /**
         * Seed size. Is approximately 1/5 the size of a cell (arbitrarily defined).
         */
        public final static int SIZE_OF_SEED = ZOOM_OF_CELL / 5;
        /**
         * orientation angle in degree.
         */
        private int angle;
        /**
         * Random X position in an interval [ - SIZE_OF_SEED ; SIZE_OF_SEED ]
         */
        private int randX;
        /**
         * Random Y position in an interval [ - SIZE_OF_SEED ; SIZE_OF_SEED ]
         */
        private int randY;

        /**
         * Constructor of GraphicSeed.
         * Define randomize position.
         */
        public GraphicSeed() {
            angle = (int) (Math.random() * 360);
            randX = -GraphicSeed.SIZE_OF_SEED + (int) (Math.random() * ((GraphicSeed.SIZE_OF_SEED - (-GraphicSeed.SIZE_OF_SEED)) + 1));
            randY = -GraphicSeed.SIZE_OF_SEED + (int) (Math.random() * ((GraphicSeed.SIZE_OF_SEED - (-GraphicSeed.SIZE_OF_SEED)) + 1));
        }

        /**
         * Getter of angle.
         *
         * @return angle of seed.
         */
        public int getAngle() {
            return angle;
        }

        /**
         * Getter of randX.
         *
         * @return random X position of seed.
         */
        public int getRandX() {
            return randX;
        }

        /**
         * Getter of randY
         *
         * @return random Y position of seed
         */
        public int getRandY() {
            return randY;
        }
    }
}
