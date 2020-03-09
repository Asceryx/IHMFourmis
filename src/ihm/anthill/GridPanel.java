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
    private boolean hideGrid;

    public GridPanel(int width, int height) {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.sizeW = width;
        this.sizeH = height;
        this.antHill = Fourmiliere.generate(width-2, height-2,10,40,2);
        this.hideGrid = false;
        new Timer(100, this).start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,500 );
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.board = (Graphics2D) g.create ();
        this.grid = new Grid(sizeW, sizeH, antHill);
        this.grid.showGrid(this.board, this.hideGrid);
    }

    public void switchHideGrid() {
        this.hideGrid = !this.hideGrid;
    }

    public static void main (String [] args) {
        JFrame frame = new JFrame("Test grid");
        GridPanel gp =  new GridPanel(10,10);
        frame.add(gp);
        frame.setDefaultCloseOperation (EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hello");
        this.hideGrid = true;
        this.antHill.evolue();
        repaint();
    }
}