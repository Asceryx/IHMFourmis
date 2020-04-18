package ihm.anthill;

import model.Fourmi;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Case extends Cell {
    private Fourmi containAnt;
    private int containSeed;
    private List<GraphicSeed> listSeed;

    public class GraphicSeed {
        public final static int SIZE_OF_SEED = ZOOM_OF_CELL * 1/5;
        private int angle;
        private int randX;
        private int randY;

        public GraphicSeed() {
            angle = (int)(Math.random() * 360);
            randX = -GraphicSeed.SIZE_OF_SEED + (int)(Math.random() * ((GraphicSeed.SIZE_OF_SEED - (-GraphicSeed.SIZE_OF_SEED)) + 1));
            randY = -GraphicSeed.SIZE_OF_SEED + (int)(Math.random() * ((GraphicSeed.SIZE_OF_SEED - (-GraphicSeed.SIZE_OF_SEED)) + 1));
        }

        public int getAngle() {
            return angle;
        }

        public int getRandX() {
            return randX;
        }

        public int getRandY() {
            return randY;
        }
    }

    public Case(int x, int y) {
        super(x, y);
        this.containAnt = null;
        this.containSeed = 0;
        this.listSeed = new ArrayList<GraphicSeed>();
    }

    public Case(int x, int y, Fourmi containAnt, int containSeed) {
        super(x,y);
        this.containAnt = containAnt;
        this.containSeed = containSeed;
        this.listSeed = new ArrayList<GraphicSeed>();
        for (int i = 0; i<containSeed; i++){
            this.listSeed.add(new GraphicSeed());
        }
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
        while (count < this.containSeed && this.containSeed > 0) {
            this.listSeed.remove(this.listSeed.size()-1);
            this.containSeed--;
        }
        while (count > this.containSeed) {
            this.listSeed.add(new GraphicSeed());
            this.containSeed++;
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

    public void paintDetailedAnt(Graphics2D grid, Fourmi ant, int translateX, int translateY) {
        grid.setColor (new Color(0, 0,0));
        int Ox = (this.x - translateX)* ZOOM_OF_CELL;
        int Oy = (this.y - translateY) * ZOOM_OF_CELL;


        int Mx = Ox + ZOOM_OF_CELL/2;
        int My = Oy + ZOOM_OF_CELL/2;
        

        grid.setColor (Color.BLACK);
        grid.drawLine(Mx, My , Ox + 3*ZOOM_OF_CELL/4, Oy + ZOOM_OF_CELL/4);
        grid.drawLine(Ox + 3*ZOOM_OF_CELL/4, Oy + ZOOM_OF_CELL/4 , Ox + 8*ZOOM_OF_CELL/10, Oy + ZOOM_OF_CELL/4);
        grid.drawLine(Mx, My , Ox + 3*ZOOM_OF_CELL/4, Oy + 3*ZOOM_OF_CELL/4);
        grid.drawLine(Ox + 3*ZOOM_OF_CELL/4, Oy + 3*ZOOM_OF_CELL/4 , Ox + 8*ZOOM_OF_CELL/10, Oy + 3*ZOOM_OF_CELL/4);
        grid.drawLine(Mx,My, Ox + 8*ZOOM_OF_CELL/10, My);

        grid.drawLine(Mx, My , Ox + ZOOM_OF_CELL/4, Oy + ZOOM_OF_CELL/4);
        grid.drawLine(Ox + ZOOM_OF_CELL/4, Oy + ZOOM_OF_CELL/4 , Ox + 2*ZOOM_OF_CELL/10, Oy + ZOOM_OF_CELL/4);
        grid.drawLine(Mx,My, Ox + 2*ZOOM_OF_CELL/10, My);
        grid.drawLine(Mx, My , Ox + ZOOM_OF_CELL/4, Oy + 3*ZOOM_OF_CELL/4);
        grid.drawLine(Ox + ZOOM_OF_CELL/4, Oy + 3*ZOOM_OF_CELL/4 , Ox + 2*ZOOM_OF_CELL/10, Oy + 3*ZOOM_OF_CELL/4);

        grid.drawLine(Mx ,Oy + ZOOM_OF_CELL/4 , Ox + 3*ZOOM_OF_CELL/4, Oy);
        grid.drawLine(Mx ,Oy + ZOOM_OF_CELL/4 , Ox + ZOOM_OF_CELL/4, Oy);

        int SIZE_OF_ITEM = (20*ZOOM_OF_CELL)/60;
        grid.setColor (Color.GREEN);
        grid.drawOval(Mx-SIZE_OF_ITEM/2 ,Oy + ZOOM_OF_CELL/4 - SIZE_OF_ITEM/2   ,  SIZE_OF_ITEM, SIZE_OF_ITEM);
        grid.fillOval(Mx-SIZE_OF_ITEM/2 ,Oy + ZOOM_OF_CELL/4 - SIZE_OF_ITEM/2   ,  SIZE_OF_ITEM, SIZE_OF_ITEM);
        SIZE_OF_ITEM = (14 * ZOOM_OF_CELL)/60;
        grid.drawOval(Mx-SIZE_OF_ITEM/2 ,Oy + 2*ZOOM_OF_CELL/4 - SIZE_OF_ITEM/2   ,  SIZE_OF_ITEM, SIZE_OF_ITEM);
        grid.fillOval(Mx-SIZE_OF_ITEM/2 ,Oy + 2*ZOOM_OF_CELL/4 - SIZE_OF_ITEM/2   ,  SIZE_OF_ITEM, SIZE_OF_ITEM);
        SIZE_OF_ITEM = (20*ZOOM_OF_CELL)/60;
        grid.drawOval(Mx-SIZE_OF_ITEM/2 ,Oy + 3*ZOOM_OF_CELL/4 - SIZE_OF_ITEM/2   ,  SIZE_OF_ITEM, SIZE_OF_ITEM);
        grid.fillOval(Mx-SIZE_OF_ITEM/2 ,Oy + 3*ZOOM_OF_CELL/4 - SIZE_OF_ITEM/2   ,  SIZE_OF_ITEM, SIZE_OF_ITEM);


        if (ant.porte()) {
            grid.setColor (Color.BLUE);
            SIZE_OF_ITEM = ZOOM_OF_CELL/2;
            grid.drawOval(Mx-SIZE_OF_ITEM/2 ,My-SIZE_OF_ITEM/2   ,  SIZE_OF_ITEM, SIZE_OF_ITEM);
            grid.fillOval(Mx-SIZE_OF_ITEM/2 ,My-SIZE_OF_ITEM/2   ,  SIZE_OF_ITEM, SIZE_OF_ITEM);
        }

        grid.setColor (new Color(0, 0,0));
    }

    public void paintDetailedSeed(Graphics2D grid, int translateX, int translateY ) {

        int Ox = (this.x - translateX) * ZOOM_OF_CELL;
        int Oy = (this.y - translateY) * ZOOM_OF_CELL;
        int Mx = Ox + ZOOM_OF_CELL/2;
        int My = Oy + ZOOM_OF_CELL/2;
        grid.setColor (new Color(0, 0,0));
        AffineTransform oldXForm = grid.getTransform();


        for (int i = 0; i<this.containSeed; i++) {
            GraphicSeed current = this.listSeed.get(i);
            int angle = current.getAngle();
            int randX = current.getRandX();
            int randY = current.getRandY();
            grid.rotate(Math.toRadians(angle), Mx + randX , My + randY);

            grid.setColor (new Color(255, 25, 25));
            grid.fillOval(Mx-GraphicSeed.SIZE_OF_SEED/2 + randX/2 ,My-2*GraphicSeed.SIZE_OF_SEED/2 + randY/2  ,  GraphicSeed.SIZE_OF_SEED, 2*GraphicSeed.SIZE_OF_SEED);
            grid.setColor (new Color(0, 0,0));
            grid.drawOval(Mx-GraphicSeed.SIZE_OF_SEED/2 + randX/2 ,My-2*GraphicSeed.SIZE_OF_SEED/2 + randY/2  ,  GraphicSeed.SIZE_OF_SEED, 2*GraphicSeed.SIZE_OF_SEED);
            grid.setColor (new Color(255, 255,255));
            grid.setTransform(oldXForm);
        }
        grid.setStroke(new BasicStroke(1));
        grid.setTransform(oldXForm);
    }

    @Override
    public void drawZoomCell(Graphics2D grid, int translateX, int translateY) {
        super.drawZoomCell(grid, translateX, translateY);
        if (this.containSeed > 0) { ;
            paintDetailedSeed(grid, translateX, translateY);
        }
        if(!this.showBorder()) {
           grid.drawRect((this.x - translateX) * ZOOM_OF_CELL, (this.y - translateY) * ZOOM_OF_CELL, ZOOM_OF_CELL, ZOOM_OF_CELL);
        }

        if(this.isContainAnt()) {
            paintDetailedAnt(grid,this.containAnt, translateX, translateY);
        }
    }




}
