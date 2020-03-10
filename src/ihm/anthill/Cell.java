package ihm.anthill;

import model.Fourmi;

import java.awt.*;

public class Cell {
    public static final int SIZE = 5;
    private int X;
    private int Y;

    public Cell(int initX, int initY) {
        this.X = initX;
        this.Y = initY;
    }

    public void paintCell (Graphics2D grid, int seedCount, boolean haveWall) {
        if (seedCount > 0) {
            grid.setColor (new Color(255, 250 - seedCount*50,250 - seedCount*50));
        }
        else if (haveWall) {
            grid.setColor (new Color(0, 0,0));
        }
        else {
            grid.setColor (new Color(255, 255,255));
        }
        grid.fillRect(this.X, this.Y,  SIZE, SIZE);
    }

    public void drawCell (Graphics2D grid) {
        grid.setColor (new Color(0, 0,0));
        grid.setStroke (new BasicStroke (1));
        grid.drawRect(this.X, this.Y,  SIZE, SIZE);
    }

    public void paintAnt(Graphics2D grid, Fourmi ant)
    {
        if (ant.porte()) {
            grid.setColor (Color.BLUE);
        }
        else
        {
            grid.setColor (Color.GREEN);
        }
        grid.fillOval(this.X, this.Y,  SIZE, SIZE);
    }

    public void drawAnt(Graphics2D grid) {
        grid.setColor (new Color(0, 0,0));
        grid.setStroke (new BasicStroke (1));
        grid.drawOval(this.X, this.Y,  SIZE, SIZE);
    }
}