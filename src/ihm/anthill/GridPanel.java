package ihm.anthill;

import model.Fourmiliere;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class GridPanel extends JPanel implements ActionListener {
    private Graphics2D board;
    private Grid grid;
    private Fourmiliere antHill;
    private int sizeW;
    private int sizeH;
    private int width;
    private int height;
    private boolean hideGrid;

    public GridPanel(int width, int height, int dimGridX, int dimGridY) {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.sizeW = dimGridX;
        this.sizeH = dimGridY;
        this.width= width;
        this.height = height;
        this.antHill = Fourmiliere.generate(dimGridX-2, dimGridY-2,40,40,40);
        this.hideGrid = false;
        new Timer(100, this).start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width,this.height );
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.board = (Graphics2D) g.create ();
        this.grid = new Grid(sizeW, sizeH, antHill);
        this.grid.showGrid(this.board, this.hideGrid);
    }

    public void switchHideGrid()
    {
        this.hideGrid = !(this.hideGrid);
    }

    public Grid getGrid()
    {
        return this.grid;
    }

    public Graphics2D getBoard()
    {
        return this.board;
    }

    public Fourmiliere getAntHill()
    {
        return this.antHill;
    }

    public boolean gethideGrid()
    {
        return this.hideGrid;
    }

    public void resetPanel()
    {
        this.antHill = new Fourmiliere(this.sizeW-2,this.sizeH-2);
        repaint();
    }


    public static void main (String [] args) {
        JFrame frame = new JFrame("Test grid");
        GridPanel gp =  new GridPanel(500,500,10,10);
        frame.add(gp);
        frame.setDefaultCloseOperation (EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.hideGrid = true;
        this.antHill.evolue();
        repaint();
    }
}