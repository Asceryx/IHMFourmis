package ihm.anthill;

import model.Fourmi;

import java.awt.*;

public abstract class Cell {
    public static final int SIZE_OF_CELL = 5;
    protected int x;
    protected int y;
    protected boolean border;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.border = true;
    }

    public Cell(int x, int y, boolean border) {
        this.x = x;
        this.y = y;
        this.border = border;
    }

    public boolean showBorder() {
        return this.border;
    }

    public void setBorder(boolean show) {
        this.border = show;
    }

    public abstract boolean isEmpty();

    public void drawCell(Graphics2D grid)
    {

        if (this.showBorder()) {
            grid.setColor (new Color(0, 0,0));
            grid.drawRect(this.x * SIZE_OF_CELL,this.y *SIZE_OF_CELL, SIZE_OF_CELL, SIZE_OF_CELL);
        }
        grid.setColor (new Color(255, 255,255));
    }
}




    /*

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
*/
