package ihm.anthill;

import model.Fourmi;
import model.Fourmiliere;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * <b>Class representing a graphical representation for anthill grid.</b>
 * <p>
 *     Based on fourmiliere.
 * </p>
 * @see Fourmiliere
 *
 * @author Julien Hayrault
 * @version 1.0
 */
public class Grid extends Fourmiliere {
    /**
     * Two dimensional list of drawing component.
     *
     * @see Cell
     * @see Wall
     * @see Case
     */
    private List<List<Cell>> grid;

    /**
     * Constructor of Grid.
     * <p>
     *     Initialize wall and blank case.
     *     Initialization is based on array created in parent class Fourmiliere.
     *     The grid is initialized with a Wall border. The other Cell are initialized empty.
     * </p>
     *
     * @param width
     * @param height
     */
    public Grid(int width, int height) {
        super(width, height);
        this.grid = new ArrayList();

        for (int i = 0; i < width ; i++) {
            List<Cell> line = new ArrayList();
            for (int j = 0; j <  height  ; j++) {
                if (this.murs[i][j]){
                    line.add(new Wall(i,j));
                }
                else {
                    line.add(new Case(i,j));
                }
            }
            this.grid.add(line);
        }
    }

    /**
     * Getter of a Cell in  a Grid.
     *
     * @param line
     * @param column
     *
     * @return The cell designated by the coordinates.
     */
    public Cell getCell(int line, int column) {
        return this.grid.get(line).get(column);
    }

    /**
     * Set a Wall at the designated coordinate.
     * <p>
     *     Check if a cell is empty before places a wall (no seed, no ant).
     * </p>
     *
     * @param x			coordonnée
     * @param y			abcisse
     * @param m			vrai si l'on veut poser un mur, faux sinon
     */
    @Override
    public void setMur(int x, int y, boolean m) {
        Cell cell = this.grid.get(x).get(y);
        super.setMur(x,y,m);
        if (m && cell.isEmpty() ){
            this.grid.get(x).set(y, new Wall(x,y));
        }
        else if (!m && cell instanceof Wall)
        {
            this.grid.get(x).set(y, new Case(x,y));
        }
    }

    /**
     * Set seed at the designated coordinate.
     * <p>
     *     Check if a cell is empty or not contain a wall and ant.
     * </p>
     * @param x		coordonnee
     * @param y		abcisse
     * @param qte	le nombre de graines que l'on souhaite poser
     */
    @Override
    public void setQteGraines(int x, int y, int qte) {
        super.setQteGraines(x,y,qte);
        Cell cell = this.grid.get(x).get(y);
        if (cell.isEmpty() || (!(cell instanceof Wall) && !(this.contientFourmi(x,y)))) {
            this.grid.get(x).set(y, new Case(x, y, null, qte));
        }
    }

    /**
     * Set ant at the designated coordinate.
     * <p>
     *     Check if a cell is empty.
     * </p>
     *
     * @param x	    coordonnee
     * @param y		abcisse
     */
    @Override
    public void ajouteFourmi(int x, int y){
        super.ajouteFourmi(x,y);
        Cell cell = this.grid.get(x).get(y);
        if(cell.isEmpty()){
            Fourmi f = this.getLesFourmis().getLast();
            Case antCase = (Case) getCell(x,y);
            antCase.setAnt(f);
        }
    }


    /**
     * Draw the grid.
     * <p>
     *     The drawing instructions are defined by the two dimension list.
     * </p>
     * @param panelDraw
     *      Support where the grid to be drawing.
     * @param show
     *      Define if graphical black grid to be show.
     */
    public void draw(Graphics2D panelDraw, boolean show) {
        int width = this.getLargeur();
        int height = this.getHauteur();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell c = getCell(i, j);
                c.setBorder(show);
                c.drawCell(panelDraw);
            }
        }
    }

    /**
     * Update the grid at t+1 step.
     * <p>
     *     <ul>
     *         <li> Update the position of ants. Get ants position before update, erase actual position. </li>
     *         <li> Update a wall. Check wall changes. </li>
     *         <li> Update a seeds. Check seed changes. </li>
     *     </ul>
     * </p>
     */
    public void update() {
        LinkedList<Fourmi> beforelesFourmis = this.getLesFourmis();
        super.evolue();
        int width = this.getLargeur() - 2;
        int height = this.getHauteur() - 2;
        for (int i = 1; i < width + 1; i++) {
            for (int j = 1; j < height + 1; j++) {
                if (this.murs[i][j]) {
                    this.setMur(i, j, true);
                }
                else if (this.qteGraines[i][j] > 0) {
                    this.setQteGraines(i, j, this.qteGraines[i][j]);
                }
                else {
                    this.grid.get(i).set(j, new Case(i,j));
                }
            }
        }
        LinkedList<Fourmi> lesFourmis = this.getLesFourmis();
        for (Fourmi fb : beforelesFourmis) {
            int x = fb.getX();
            int y = fb.getY();
            Case antDel = (Case) getCell(x,y);
            antDel.setAnt(null);
        }
        for (Fourmi f : lesFourmis)
        {
            int x = f.getX();
            int y = f.getY();
            Case antCase = (Case) getCell(x,y);
            antCase.setAnt(f);
        }
    }


    /**
     * Generate a random grid, with random placement of wall, ant and seed.
     * Probability value means: 1 chance of value.
     *
     * @param probSeed
     * @param probAnt
     * @param probWall
     */
    public void generate(int probSeed, int probAnt, int probWall) {
        int width = this.getLargeur()-2;
        int height = this.getHauteur()-2;
        for(int i = 1; i<width+1; i++) {
            for (int j = 1; j < height + 1; j++) {
                if (probSeed > 0 && new Random().nextInt(probSeed) == 0) {
                    int graine = new Random().nextInt(QMAX) + 1;
                    this.setQteGraines(i, j, graine);
                } else if (probAnt > 0 && new Random().nextInt(probAnt) == 0) {
                    this.ajouteFourmi(i, j);
                } else if (probWall > 0 && new Random().nextInt(probWall) == 0) {
                    this.setMur(i, j, true);
                }
            }
        }
    }
}