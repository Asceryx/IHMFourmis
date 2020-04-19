package ihm.anthill.field;

import ihm.anthill.field.Cell;

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

    @Override
    public void drawZoomCell(Graphics2D grid, int translateX, int translateY) {
        super.drawZoomCell(grid, translateX, translateY);
        int Ox = (this.x - translateX) * ZOOM_OF_CELL;
        int Oy = (this.y - translateY) * ZOOM_OF_CELL;
        int OxMax = Ox + ZOOM_OF_CELL;
        int OyMax = Oy + ZOOM_OF_CELL;
        grid.setColor(new Color(0, 0, 0));


        int dx;
        int dy;
        int offset;

        for (int i = 0; i * ZOOM_OF_CELL / 6 <= ZOOM_OF_CELL; i++) {
            dy = i * ZOOM_OF_CELL / 6;
            grid.drawLine(Ox, Oy + dy, OxMax, Oy + dy);
        }


        for (int z = 0; z * ZOOM_OF_CELL / 6 < ZOOM_OF_CELL; z++) {
            offset = z * -ZOOM_OF_CELL / 6;
            for (int j = 1; offset + j * ZOOM_OF_CELL / 3 <= ZOOM_OF_CELL ; j++) {
                dx = offset + j * ZOOM_OF_CELL / 3;
                if (offset + j * ZOOM_OF_CELL / 3 > 0) {
                    grid.drawLine(Ox + dx, Oy + z * ZOOM_OF_CELL / 6, Ox + dx, Oy + (z + 1) * ZOOM_OF_CELL / 6);
                }
            }
        }
    }
}
