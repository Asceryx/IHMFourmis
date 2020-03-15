package ihm.anthill;


import model.Fourmiliere;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class GridComponent extends JComponent implements ActionListener, MouseListener, KeyListener, MouseWheelListener  {

    private Grid grid;
    private int width;
    private int height;
    private boolean showGrid;
    private Graphics2D graphics;
    boolean eventKey[];

    public GridComponent(int width, int height) {
        this.setPreferredSize(new Dimension(width*Cell.SIZE_OF_CELL, height*Cell.SIZE_OF_CELL));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.grid = new Grid(width, height);
        this.width = width;
        this.height = height;
        this.showGrid = true;
        this.eventKey = new boolean[2];
        addKeyListener (this);
        addMouseListener(this);
        addMouseWheelListener (this) ;
        setOpaque(true);
        this.setFocusable(true);
        new Timer(100, this).start();
    }

    public void setShowGrid(boolean show){
        this.showGrid = show;
        repaint();
    }

    public void switchShowGrid(boolean show) {
        setShowGrid(!(this.showGrid));
    }

    public void reset(){
        this.grid = new Grid(width, height);
    }

    public void generation(int probSeed, int probAnt,  int probWall) {
        this.grid.generate(probSeed, probAnt,  probWall);
    }

    public Grid getGrid(){
        return this.grid;
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g.create();
        this.grid.draw(graphics, this.showGrid);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.eventKey[0] = true;
        int mousex = e.getX();
        int mousey = e.getY();

        int i = mousex / Cell.SIZE_OF_CELL;
        int j = mousey / Cell.SIZE_OF_CELL;
        System.out.println(i + " ; " + j);


        if (this.eventKey[0] && this.eventKey[1])
        {
            System.out.println("Mouse clicked + Shift pressed");
            this.grid.ajouteFourmi(i,j);
        }
        else if (this.eventKey[0])
        {
            System.out.println("Mouse clicked");
            boolean bool = this.grid.getMur(i,j);
            this.grid.setMur(i,j,!bool);
        }
         repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.eventKey[0] = false;
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            this.eventKey[1] =true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.eventKey[1] =false;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int rotation = e.getWheelRotation () ;
        System.out.println(e.getX()/Cell.SIZE_OF_CELL + ";"+ e.getY()/Cell.SIZE_OF_CELL);
        int i = e.getX()/Cell.SIZE_OF_CELL;
        int j = e.getY()/Cell.SIZE_OF_CELL;
        Cell cell = this.grid.getCell(i,j);
        int seed = this.grid.getQteGraines(i,j) + (-rotation);
        if (seed < Fourmiliere.QMAX && seed >= 0)
        {
            this.grid.setQteGraines(i,j,seed);
        }
        else if (seed >= Fourmiliere.QMAX) {
            this.grid.setQteGraines(i,j,4);
        }
        else if (seed < 0) {
            this.grid.setQteGraines(i,j,0);
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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


    public static void main (String [] args){
        JFrame frame = new JFrame("Drawing Line");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        GridComponent g = new GridComponent(100,100);
        g.generation(20,20,20);
        panel.add(g);
        frame.add(panel);
        frame.setDefaultCloseOperation (EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(200,200));
        frame.pack() ;
        frame.setVisible(true);
    }
}
