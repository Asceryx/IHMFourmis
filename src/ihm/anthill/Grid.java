package ihm.anthill;

import model.Fourmi;
import model.Fourmiliere;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Grid extends Fourmiliere {
    private List<List<Cell>> grid;

    public Grid(int width, int height) {
        super(width, height);
        // Génération de la grid
        this.grid = new ArrayList();


        for (int i = 0; i < width ; i++) {
            List<Cell> line = new ArrayList();
            for (int j = 0; j <  height  ; j++) {
                if (this.murs[i][j]){
                    line.add(new Wall(i,j));
                }
                else {
                    line.add(new Case(i,j));
                }
            }
            this.grid.add(line);
        }

    }

    public Cell getCell(int line, int column) {
        return this.grid.get(line).get(column);
    }

    @Override
    public void setMur(int x, int y, boolean m) {
        Cell cell = this.grid.get(x).get(y);
        super.setMur(x,y,m);
        if (m && cell.isEmpty() ){
            this.grid.get(x).set(y, new Wall(x,y));
        }
        /*
        else if (cell instanceof Wall){
            this.grid.get(x).set(y, new Case(x,y));
        }

         */


    }

    @Override
    public void setQteGraines(int x, int y, int qte) {
        super.setQteGraines(x,y,qte);
        Cell cell = this.grid.get(x).get(y);
        if (cell.isEmpty() || (!(cell instanceof Wall) && !(this.contientFourmi(x,y)))) {
            this.grid.get(x).set(y, new Case(x, y, null, qte));
        }
    }

    @Override
    public void ajouteFourmi(int x, int y){
        super.ajouteFourmi(x,y);
        Cell cell = this.grid.get(x).get(y);
        if(cell.isEmpty()){
            Fourmi f = this.getLesFourmis().getLast();
            Case antCase = (Case) getCell(x,y);
            antCase.setAnt(f);
        }
    }



    public void draw(Graphics2D panelDraw, boolean show) {
        int width = this.getLargeur();
        int height = this.getHauteur();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell c = getCell(i, j);
                c.setBorder(show);
                c.drawCell(panelDraw);
            }
        }
    }

    public void update() {
        LinkedList<Fourmi> beforelesFourmis = this.getLesFourmis();
        super.evolue();
        int width = this.getLargeur() - 2;
        int height = this.getHauteur() - 2;

        for (int i = 1; i < width + 1; i++) {
            for (int j = 1; j < height + 1; j++) {
                if (this.murs[i][j]) {
                    this.setMur(i, j, true);
                }
                else if (this.qteGraines[i][j] > 0) {
                    this.setQteGraines(i, j, this.qteGraines[i][j]);
                }
                else {
                    this.grid.get(i).set(j, new Case(i,j));
                }
            }
        }
        LinkedList<Fourmi> lesFourmis = this.getLesFourmis();
        for (Fourmi fb : beforelesFourmis) {
            int x = fb.getX();
            int y = fb.getY();
            Case antDel = (Case) getCell(x,y);
            antDel.setAnt(null);
        }
        for (Fourmi f : lesFourmis)
        {
            int x = f.getX();
            int y = f.getY();
            Case antCase = (Case) getCell(x,y);
            antCase.setAnt(f);
        }



    }




    public void generate(int probSeed, int probAnt, int probWall) {
        int width = this.getLargeur()-2;
        int height = this.getHauteur()-2;
        for(int i = 1; i<width+1; i++) {
            for (int j = 1; j < height + 1; j++) {
                if (new Random().nextInt(probSeed) == 0) {
                    int graine = new Random().nextInt(QMAX) + 1;
                    this.setQteGraines(i, j, graine);
                } else if (new Random().nextInt(probAnt) == 0) {
                    this.ajouteFourmi(i, j);
                } else if (new Random().nextInt(probWall) == 0) {
                    this.setMur(i, j, true);
                }
            }
        }
    }
}