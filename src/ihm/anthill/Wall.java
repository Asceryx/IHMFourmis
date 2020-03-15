package ihm.anthill;

import java.awt.*;

public class Wall extends Cell {

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public void drawCell(Graphics2D grid) {
        super.drawCell(grid);
        grid.drawRect(this.x * SIZE_OF_CELL, this.y * SIZE_OF_CELL,  SIZE_OF_CELL, SIZE_OF_CELL);
        grid.setColor (new Color(0, 0,0));
        grid.fillRect(this.x * SIZE_OF_CELL, this.y * SIZE_OF_CELL,  SIZE_OF_CELL, SIZE_OF_CELL);
    }

}
