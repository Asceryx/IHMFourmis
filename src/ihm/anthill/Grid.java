package ihm.anthill;

import model.Fourmi;
import model.Fourmiliere;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;


public class Grid extends Fourmiliere {
    private  ArrayList<ArrayList<Cell>> listCell;
    Fourmiliere antHill;
    private int width;
    private int height;

    public Grid(int width, int height, Fourmiliere initAntHill) {
        super(width,height);
        this.antHill = initAntHill;
        this.listCell = new ArrayList();
        for(int i = 0; i<width; i++)
        {
            ArrayList<Cell> lineCell = new ArrayList();
            for(int j = 0; j<height; j++)
            {
                Cell cell = new Cell(10 + i*Cell.SIZE, 10 + j*Cell.SIZE);
                // Decalage de 10px, pour voir les bordures
                lineCell.add(cell);
            }
            this.listCell.add(lineCell);
        }
        this.width = width;
        this.height = height;
    }

    public ArrayList<ArrayList<Cell>> getListCell() {
        return listCell;
    }

    public Cell getCell(int line, int column){
        return this.listCell.get(line).get(column);
    }

    public void showGrid(Graphics2D grid, boolean showCell) {
        System.out.println(showCell);
        for (int i = 0; i < this.width; i++)
            for (int j = 0; j < this.height; j++) {
                getCell(i, j).paintCell(grid, antHill.getQteGraines(i,j), antHill.getMur(i,j));
                int finalI = i;
                int finalJ = j;
                Optional<Fourmi> antFind = antHill.getLesFourmis().stream()
                        .filter(f -> f.getX() == finalI)
                        .filter(f -> f.getY() == finalJ)
                        .findFirst();
                if (antFind.isPresent()) {
                    getCell(i, j).paintAnt(grid, antFind.get());
                }
                if (showCell == true) {
                    getCell(i, j).drawCell(grid);
                }
            }
    }


}