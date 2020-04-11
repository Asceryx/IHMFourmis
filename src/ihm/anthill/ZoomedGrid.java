package ihm.anthill;

import java.awt.*;

public class ZoomedGrid extends Grid {
    /**
     * Constructor of Grid.
     * <p>
     * Initialize wall and blank case.
     * Initialization is based on array created in parent class Fourmiliere.
     * The grid is initialized with a Wall border. The other Cell are initialized empty.
     * </p>
     *
     * @param width
     * @param height
     */
    private int width;
    private int height;
    private int mouseX;
    private int mouseY;
    private Grid parent;

    public ZoomedGrid(int width, int height, int x, int y, Grid grid) {
        super(width, height);
        this.mouseX = x;
        this.mouseY = y;
        this.parent = grid;
        this.width = width;
        this.height = height;

    }

    public int[] area(){
        int areaX = this.mouseX - (this.width/2);
        int areaY = this.mouseY - (this.height/2);
        int endAreaX = this.mouseX + (this.width/2);
        int endAreaY = this.mouseY + (this.height/2);

        if (areaX < 0){
            areaX = 0;
            endAreaX += Math.abs(this.mouseX - (width/2));
        }
        if (areaY < 0) {
            areaY = 0;
            endAreaY += Math.abs(this.mouseY - (height/2));
        }

        if (endAreaX > this.parent.getLargeur()){
            areaX -= (endAreaX - this.parent.getLargeur());
            endAreaX = this.parent.getLargeur();
        }

        if (endAreaY > this.parent.getHauteur()){
            areaY -= (endAreaY - this.parent.getHauteur());
            endAreaY = this.parent.getHauteur();
        }


        return new int[]{areaX, areaY, endAreaX, endAreaY};
    }

    @Override
    public void draw(Graphics2D panelDraw, boolean show) {
        int width = this.getLargeur();
        int height = this.getHauteur();
        int [] area = area();
        for (int i = area[0] ; i <= area[2]; i++) {
            for (int j = area[1]; j <= area[3]; j++) {
                Cell c = this.parent.getCell(i, j);
                c.setBorder(show);
                c.drawZoomCell(panelDraw);
            }
        }
    }
}
