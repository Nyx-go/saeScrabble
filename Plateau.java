public class Plateau {
	private Case [][] g; // g pour grille

	int [][] plateau = 
	{{5 , 1 , 1 , 2 , 1 , 1 , 1 , 5 , 1 , 1 , 1 , 2 , 1 , 1 , 5},
	 {1 , 4 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 4 , 1},
	 {1 , 1 , 4 , 1 , 1 , 1 , 2 , 1 , 2 , 1 , 1 , 1 , 4 , 1 , 1},
	 {2 , 1 , 1 , 4 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 4 , 1 , 1 , 2},
	 {1 , 1 , 1 , 1 , 4 , 1 , 1 , 1 , 1 , 1 , 4 , 1 , 1 , 1 , 1},
	 {1 , 3 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 3 , 1},
	 {1 , 1 , 2 , 1 , 1 , 1 , 2 , 1 , 2 , 1 , 1 , 1 , 2 , 1 , 1},
	 {5 , 1 , 1 , 2 , 1 , 1 , 1 , 4 , 1 , 1 , 1 , 2 , 1 , 1 , 5},
	 {1 , 1 , 2 , 1 , 1 , 1 , 2 , 1 , 2 , 1 , 1 , 1 , 2 , 1 , 1},
	 {1 , 3 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 3 , 1},
	 {1 , 1 , 1 , 1 , 4 , 1 , 1 , 1 , 1 , 1 , 4 , 1 , 1 , 1 , 1},
	 {2 , 1 , 1 , 4 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 4 , 1 , 1 , 2},
	 {1 , 1 , 4 , 1 , 1 , 1 , 2 , 1 , 2 , 1 , 1 , 1 , 4 , 1 , 1},
	 {1 , 4 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 4 , 1},
	 {5 , 1 , 1 , 2 , 1 , 1 , 1 , 5 , 1 , 1 , 1 , 2 , 1 , 1 , 5}} ;

	public Plateau () {
		this.g = new Case [15][15];
		for (int i=0 ; i < 15 ; i++) {
			for (int j=0 ; j < 15 ; j++) {
				this.g [i][j] = new Case (this.plateau [i][j]);
			}
		}
	}

	public Plateau (Case[][] plateau) {
		this.g = plateau;
	}
	
	/*résultat : chaîne décrivant ce Plateau*/
	public String toString (){
		String tabFinal = "*  | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |10 |11 |12 |13 |14 |15 |";
		int colonne = 0 ; int ligne = 0 ;
		boolean isSepar=false;

		String SeparBas = "|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|" + "/n";
		String SeparHaut ="|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |" + "/n";

		while (ligne < 15) {
			if (isSepar == false) {
				tabFinal = tabFinal + "~  " + SeparBas + "/n~  " + SeparHaut + "/n";
				isSepar = true ;
			}
			else {
				ligne++;

				if (ligne < 10) {
					tabFinal = tabFinal + ligne + "  | ";
				}
				else {
					tabFinal = tabFinal + ligne + " | ";
				} // évite le décallage quand la ligne passe à 2 chiffres
				
				for (colonne=0 ; colonne<15 ; colonne++) {
					if (this.g[ligne-1][colonne].estRecouverte()==true) {
						tabFinal = tabFinal + this.g[ligne-1][colonne].getLettre() + " | ";
					}
					else {
						if (this.g[ligne-1][colonne].getCouleur()==1) {
							tabFinal = tabFinal + "  | ";
						}
						else {
							tabFinal = tabFinal + this.g[ligne-1][colonne].getCouleur() + " | ";
						}
						
					}
				}

				tabFinal = tabFinal + "/n";
				isSepar = false;
			}
		}
		tabFinal = tabFinal + "*  " + SeparBas ;

		return tabFinal;
	}

	/* pré-requis : mot est un mot accepté par CapeloDico,
	* 0 <= numLig <= 14, 0 <= numCol <= 14, sens est un élément
	* de {’h’,’v’} et l’entier maximum prévu pour e est au moins 25
	* résultat : retourne vrai ssi le placement de mot sur this à partir
	* de la case (numLig, numCol) dans le sens donné par sens à l’aide
	* des jetons de e est valide.
	*/
	
	/*public boolean placementValide(String mot, int numLig, int numCol, char sens, MEE e) {
	*	boolean isValide = false;
	*
	*	if (mot.length>=2) {
	*
	*	}
	*
	*	return isValide;
	}*/
}