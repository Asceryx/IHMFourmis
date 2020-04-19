package ihm.anthill.field;
import java.awt.*;

public abstract class Cell {
    public static final int SIZE_OF_CELL = 5;
    public static final int ZOOM_OF_CELL = 30;
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
            grid.drawRect(this.x * SIZE_OF_CELL ,this.y *SIZE_OF_CELL , SIZE_OF_CELL , SIZE_OF_CELL );
        }
        grid.setColor (new Color(255, 255,255));
    }

    public void drawZoomCell(Graphics2D grid, int translateX, int translateY)
    {
        if (this.showBorder()) {
            grid.setColor (new Color(0, 0,0));
            grid.drawRect((this.x - translateX) * ZOOM_OF_CELL ,(this.y - translateY)*ZOOM_OF_CELL , ZOOM_OF_CELL , ZOOM_OF_CELL );
        }
        grid.setColor (new Color(255, 255,255));
    }
}