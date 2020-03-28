package ihm.anthill;

import model.Fourmi;

import java.awt.*;

public class Case extends Cell {
    private Fourmi containAnt;
    private int containSeed;

    public Case(int x, int y) {
        super(x, y);
        this.containAnt = null;
        this.containSeed = 0;
    }

    public Case(int x, int y, Fourmi containAnt, int containSeed) {
        super(x,y);
        this.containAnt = containAnt;
        this.containSeed = containSeed;
    }

    public boolean isContainAnt()
    {
        return !(this.containAnt==null);
    }

    public boolean isContainSeed()
    {
        return !(this.containSeed==0);
    }

    @Override
    public boolean isEmpty() {
        return !(this.isContainAnt()) && !(this.isContainSeed());
    }

    public void setAnt(Fourmi ant) {
        if (!(isContainAnt())) {
            this.containAnt = ant;
        }
    }

    public Fourmi getAnt() {
        return this.containAnt;
    }

    public void setSeed(int count) {
        this.containSeed = count;
    }

    @Override
    public void drawCell(Graphics2D grid) {
        super.drawCell(grid);
        if (this.containSeed > 0) {
            grid.setColor (new Color(255, 250 - this.containSeed*50,250 - this.containSeed*50));
        }
        if(!this.showBorder()) {
            grid.drawRect(this.x * SIZE_OF_CELL, this.y * SIZE_OF_CELL, SIZE_OF_CELL, SIZE_OF_CELL);
        }
        grid.fillRect(this.x * SIZE_OF_CELL+1, this.y * SIZE_OF_CELL+1,  SIZE_OF_CELL-1, SIZE_OF_CELL-1);

        if(this.isContainAnt()) {
            paintAnt(grid,this.containAnt);
        }


    }

    public void paintAnt(Graphics grid, Fourmi ant) {
        grid.setColor (new Color(255, 255,255));
        grid.drawOval(this.x * SIZE_OF_CELL+1, this.y * SIZE_OF_CELL+1,  SIZE_OF_CELL-2, SIZE_OF_CELL-2);

        if (ant.porte()) {
            grid.setColor (Color.BLUE);
        }
        else {
            grid.setColor (Color.GREEN);
        }

        grid.fillOval(this.x * SIZE_OF_CELL+1, this.y * SIZE_OF_CELL+1,  SIZE_OF_CELL-2, SIZE_OF_CELL-2);
    }
}
