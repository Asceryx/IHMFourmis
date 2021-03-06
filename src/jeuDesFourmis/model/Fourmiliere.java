package jeuDesFourmis.model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe de gestion de la fourmiliere
 * @author abergey
 * @version 1.1
 */


public class Fourmiliere {

	private int largeur, hauteur ;

	// la liste des fourmis de la fourmiliere.
	// Attention : la position X,Y d'une fourmi doit correspondre à un booleen true
	// dans le tableau fourmis
	private LinkedList<Fourmi> lesFourmis ;

	// Tableaux contenant les murs, les fourmis et les graines.
	// Attention : pour un terrain [1..hauteur]x[1..largeur], ces tableaux
	// sont indicés de [0..hauteur+1][0..largeur+1], cela permet de simplifier
	// certains traitements en ne traitant pas le cas particulier des bordures.
	protected boolean murs[][];
	protected boolean fourmis[][];
	protected int qteGraines[][];
	public static final int QMAX = 4;
	private int totalGraine;
	private int totalFourmis;

	/**
	 * Crée une fourmiliere de largeur l et de hauteur h.
	 * @param l			largeur
	 * @param h			hauteur
	 */
	public Fourmiliere(int l, int h) {
		largeur = l;
		hauteur = h;

		this.lesFourmis = new LinkedList<Fourmi>();
		this.totalGraine = 0;
		this.totalFourmis = 0;

		fourmis = new boolean[hauteur+2][largeur+2];
		for (int i =0 ; i < hauteur+2 ; i++) {
			for (int j = 0; j < largeur + 2; j++) {
				fourmis[i][j] = false;
			}
		}

		murs = new boolean[hauteur+2][largeur+2];
		for (int i =0 ; i < hauteur+2 ; i++) {
			for (int j = 0; j < largeur + 2; j++) {
				murs[i][j] = (i == 0) || (i == hauteur-1 ) || (j == 0) || (j == largeur -1);
			}
		}

		qteGraines = new int[hauteur+2][largeur+2];
		for (int i =0 ; i < hauteur+2 ; i++) {
			for (int j = 0; j < largeur + 2; j++) {
				qteGraines[i][j] = 0;
			}
		}
	}


	/**
	 * Retourne la largeur de la fourmiliere
	 * @return 			la hauteur
	 */
	public int getLargeur() {
		return largeur;
	}

	/**
	 * Retourne la largeur de la fourmiliere
	 * @return			la hauteur
	 */
	public int getHauteur() {
		return hauteur;
	}

	/**
	 * Presence d'un mur au point  (x,y) du terrain
	 * @param x			coordonnée
	 * @param y			abcisse
	 * @return			vrai si il y a un mur
	 */
	public boolean getMur(int x, int y) {
		return murs[x][y];
	}

	/**
	 * Positionne un mur en au point (x,y) du terrain
	 * @param x			coordonnée
	 * @param y			abciss'e
	 * @param m			vrai si l'on veut poser un mur, faux sinon
	 */
	public void setMur(int x, int y, boolean m) {
		assert (x>0 && x <hauteur+1 && y > 0 && y <largeur+1);
		murs[x][y]=m;
	}

	/**
	 * Presence  d'une fourmi au point (x,y) du terrain
	 * @param x		coordonnee
	 * @param y		abcisse
	 * @return		vrai si il y a une fourmi
	 */
	public boolean contientFourmi(int x, int y){
		return fourmis[x][y];
	}

	/**
	 * Ajoute (ou remplace) une fourmi non chargée au point (x,y) du terrain
	 * @param x	    coordonnee
	 * @param y		abcisse
	 */
	public void ajouteFourmi(int x, int y){
		if (!fourmis[x][y] && !murs[x][y]) {
			Fourmi f = new Fourmi(x,y,false);
			fourmis[x][y]=true ;
			lesFourmis.add(f);
			this.totalFourmis +=1;
		};
	}

	/**
	 * Retourne la quantité de graine au point (x,y) du terrain
	 * @param x		coordonnnee
	 * @param y		abcisse
	 * @return		la quantité de graine
	 */
	public int getQteGraines(int x,  int y) {
		return this.qteGraines[x][y];
	}

	/**
	 * Positionne des graines au point (x,y) du terrain
	 * @param x		coordonnee
	 * @param y		abcisse
	 * @param qte	le nombre de graines que l'on souhaite poser
	 */
	public void setQteGraines(int x, int y, int qte) {
		assert (qte >=0 && qte <=QMAX);
		this.qteGraines[x][y]=qte;
	}

	/**
	 * Compte les graines du point (x,y) et des cellules voisines
	 * Les voisines s'entendent au sens de 8-connexité.
	 * On ne compte pas les graines sur les murs)
	 * @param x		coordonnee
	 * @param y		abcisse
	 * @return		le nombre de graines
	 * */

	private int compteGrainesVoisines(int x, int y) {
		assert (x>0 && x <hauteur+1 && y > 0 && y <largeur+1);
		int nb = 0 ;
		for (int vx = -1 ; vx < 2 ; vx++) {
			for (int vy = -1; vy < 2; vy++) {
				if (!murs[x + vx][y + vy]) {
					nb = nb + qteGraines[x + vx][y + vy];
				}
			}
		}
		return nb ;
	}

	/**
	 * Evolution d'une étape de la fourmilière
	 * Pour chaque fourmi f de la foumilière.
	 *     1) si il y a une(ou des) graines sur la case, et que
	 *       la fourmi ne porte rien :
	 *     		on choisit aléatoirement de charger ou non une graine,
	 *                  en fonction du nombre de graines autour.
	 *     2) f se deplace aléatoirement d'une case (en évitant les murs)
	 *     3) si f est chargée et qu'il reste de la place pour une graine,
	 *          on choisit aléatoirement de poser ou non  la graine,
	 *          en fonction du nombre de graines autour.
	 *
	 */

	public void evolue() {
		Iterator<Fourmi> ItFourmi = lesFourmis.iterator();
		while (ItFourmi.hasNext()) {
			Fourmi f = ItFourmi.next();
			int posX = f.getX();
			int posY = f.getY();
			if (!f.porte() &&  qteGraines[f.getX()][f.getY()]>0){   // la fourmi f prend ?
				if (Math.random()<Fourmi.probaPrend(compteGrainesVoisines(posX,posY))){
					f.prend();
					qteGraines[posX][posY]--;
				}
			}
			int deltaX ;  // la fourmi f se déplace.
			int deltaY ;
			int cptEssai = 0 ;
			do {
				cptEssai++ ;
				deltaX = posX ;
				deltaY = posY ;
				if (cptEssai >= 100) {
					break;
				}
				int tirage  = (int) (Math.random() *7.99999999);
				switch(tirage) {
					case 0:
						deltaX-- ;
						deltaY-- ;
						break;
					case 1:
						deltaY-- ;
						break ;
					case 2:
						deltaX++;
						deltaY--;
						break ;
					case 3:
						deltaX-- ;
						break ;
					case 4:
						deltaX++ ;
						break;
					case 5:
						deltaX-- ;
						deltaY++ ;
						break;
					case 6:
						deltaY++ ;
						break ;
					case 7:
						deltaX++ ;
						deltaY++ ;
						break;
				}
			}
			while(murs[deltaX][deltaY] || fourmis[deltaX][deltaY]);


			fourmis[posX][posY]=false;
			fourmis[deltaX][deltaY]=true;
			f.setX(deltaX);
			f.setY(deltaY);
			// la fourmi pose ?
			if (f.porte() && qteGraines[deltaX][deltaY]<QMAX){
				if (Math.random()<Fourmi.probaPose(compteGrainesVoisines(deltaX,deltaY))){
					f.pose();
					qteGraines[deltaX][deltaY]++;
				}
			}
		}
	}

	// méthodes d'affichage
	/**
	 * Retourne une chaine affichant les graines sur le terrain
	 *    (X pour un mur ou 1 entier pour le nbgraines)
	 * @return 	la chaine représentant le terrain
	 */
	public String stringGraines() {
		String res = "" ;
		for (int i =0 ; i < hauteur+2; i++) {
			for (int j =0 ; j < largeur+2; j++)
				if (murs[i][j])
					res = res + "X";
				else {
					if (qteGraines[i][j]>0){
						String qS = String.format("%d", qteGraines[i][j])	;
						res = res + qS ;
					}
					else
						res = res +".";
				}
			res = res + "\n";
		}
		return res ;
	}

	/**
	 * Retourne une chaine affichant les fourmis sur le terrain
	 *      (X pour un mur ou 0 pour la fourmi)
	 * @return 	la chaine représentant le terrain.
	 */
	public String stringFourmis() {
		String res = "" ;
		for (int i =0 ; i < hauteur+2; i++) {
			for (int j =0 ; j < largeur+2; j++)
				if (murs[i][j])
					res = res + "X";
				else if (fourmis[i][j])	{
					res = res + "O";
				}
				else
					res = res + ".";
			res = res + "\n";
		}
		return res ;
	}

	/**
	 * Retourne une chaine affichant le le terrain (X pour un mur)
	 * @return 	la chaine représentant le terrain.
	 */
	public String stringMurs() {
		String res = "" ;
		for (int i =0 ; i < hauteur+2 ; i++) {
			for (int j =0 ; j < largeur+2 ; j++)
				res = res + (murs[i][j]?"X":".");
			res = res + "\n";
		}
		return res ;
	}

	public int getTotalSeed() {
		this.totalGraine = 0;
		for (int i = 0; i < hauteur + 2; i++) {
			for (int j = 0; j < largeur + 2; j++) {
				this.totalGraine += qteGraines[i][j];
			}
		}
		return this.totalGraine;
	}

	public int getTotalAnt()
	{
		return this.totalFourmis;
	}

	public LinkedList<Fourmi> getLesFourmis() {
		return this.lesFourmis;
	}



	/**
	 * Exemple de main manipulant une fourmiliere
	 *  de largeur 20 et de hauteur 10.
	 * @param args
	 * 		args.
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub

		// La fourmilere
		Fourmiliere f = new Fourmiliere(20,10);

		// On crée quelques murs
		for (int i =1; i <4; i++)
			f.setMur(i, 2*i, true);
		// On ajoute 3 fourmis dans la fourmilière
		f.ajouteFourmi(1, 1);
		f.ajouteFourmi(2, 2);
		f.ajouteFourmi(3, 3);
		// On pose des graines
		for (int i =0; i <10; i++){
			f.setQteGraines(i, 2*i, 1);
			f.setQteGraines(11-i, 2*i, 1);
		}

		// On affiche les probabilités de prise et dépot.
		System.out.println("proba      Prise      Pause ");
		for (int i = 0 ; i <24 ; i++){
			System.out.println("   "+i+"   "+String.format("%f2", Fourmi.probaPrend(i))+"  "+
					String.format("%f2",Fourmi.probaPose(i)));
		}

		// On affiche le terrain initial.
		System.out.println(f.stringMurs());
		System.out.println(f.stringGraines());
		System.out.println(f.stringFourmis());

		// On fait evoluer la fourmiliere
		for (int i =1 ; i <10000 ; i++) {
			f.evolue();
			System.out.println("-------------------- "+i+" ----------------------------------");
			System.out.println("---------------------      --------------------------------");
			System.out.println(f.stringMurs());
			System.out.println(f.stringGraines());
			System.out.println(f.stringFourmis());
		}
	}

}
