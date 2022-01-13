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
	
	public boolean placementValide(String mot, int numLig, int numCol, char sens, MEE e) {
		boolean isValide = false; boolean isStringValide = capelDico(mot);
		boolean isCaseRec = false; boolean isCaseNotRec = false; boolean isOneNotEqual = false;
		char[] cArray = mot.toCharArray(); char[] cPresTab = new char [mot.length()]; 
		int[] strIndex = new int [] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		MEE eTempo = new MEE (e);
		int index ; int i = 0;

		if (isStringValide == true) {
			if (numCol == 8 && numLig == 8 && this.g[8][8].estRecouverte()==false) {
				if (mot.length()>=2) {
					isValide = true; //forcer l'entrée dans la boucle
					while ( i < cArray.length && isValide==true) {
						index = Ut.majToIndex(cArray[i]);
						isValide = eTempo.retire(index);
						i++;
					}
				}
			}

			else {
				if (sens == 'v' && numLig+cArray.length-1 < 15 ) {
					if (numLig == 1 && 
						this.g[cArray.length+1][numLig].estRecouverte()==false ||

						this.g[numLig-1][numCol].estRecouverte()==false &&
						numLig+mot.length()==15 ||

						this.g[numLig-1][numCol].estRecouverte()==false &&
						this.g[cArray.length+1][numCol].estRecouverte()==false) {

						while (isCaseRec == false && isCaseNotRec == false && i < mot.length()) {
							isCaseRec = this.g[numLig+i][numCol].estRecouverte();

							if (isCaseRec == false) {
								isCaseNotRec = true;
								cPresTab[i] = 0 ;
							}
							else {
								cPresTab[i] = this.g[numLig+i][numCol].getLettre();
							}
							i++;
						}

					}
				}
				else if (sens == 'h' && numCol+cArray.length-1 < 15) {
					if (numCol == 1 && 
						this.g[numLig][cArray.length+1].estRecouverte()==false ||

						this.g[numLig][numCol-1].estRecouverte()==false &&
						numCol+mot.length()==15 ||

						this.g[numLig][numCol-1].estRecouverte()==false &&
						this.g[numLig][cArray.length+1].estRecouverte()==false ) {

						while (isCaseRec == false && isCaseNotRec == false && i < mot.length()) {
							isCaseRec = this.g[numLig][numCol+i].estRecouverte();

							if (isCaseRec == false) {
								isCaseNotRec = true;
								cPresTab[i] = 0 ;
							}
							else {
								cPresTab[i] = this.g[numLig][numCol+i].getLettre();
							}
							i++;
						}

					}
				}

				if (isCaseRec == true && isCaseNotRec == true ) {
					while ( i < mot.length() && isOneNotEqual == false ) {
						if (cPresTab[i] != 0 ) {
							isOneNotEqual = !(cPresTab[i]==cArray[i]);
							index = Ut.majToIndex(cArray[i]);
							eTempo.ajoute(index);
						}
						i++;
					}

					if (isOneNotEqual == false) {
						isValide = true; //forcer l'entrée dans la boucle
						i = 0;
						while ( i < cArray.length && isValide==true) {
							index = Ut.majToIndex(cArray[i]);
							isValide = eTempo.retire(index);
							i++;
						}
					}
				}
			}
		}
		return isValide;
	}

	public boolean capelDico (String mot) {
		boolean isValide;
		Ut.afficher("Saisir si " + mot + " est valide (Dans le dico + maj): "); isValide=Ut.saisirBooleen();
		return isValide;
	}

	/* pré-requis : le placement de mot sur this à partir de la case
	* (numLig, numCol) dans le sens donné par sens est valide
	* résultat : retourne le nombre de points rapportés par ce placement, le
	* nombre de points de chaque jeton étant donné par le tableau nbPointsJet.*/
	public int nbPointsPlacement(String mot, int numLig, int numCol,
	char sens, int[] nbPointsJet, MEE e) {
		int countMultiply = 1; int countAdd = 0; int scrabble = 0; int i;
		char[] cArray = mot.toCharArray();
		MEE eTempo = new MEE (e); //Rajout de e pour vérifier les 50pts d'un Scrabble

		if (sens == 'v') {
			for (i = 0 ; i < mot.length() ; i++) {
				countMultiply = countMultiply * this.g[numLig+i][numCol].getCouleur();
			}
		}
		else {
			for (i = 0 ; i < mot.length() ; i++) {
				countMultiply = countMultiply * this.g[numLig][numCol+i].getCouleur();
			}
		}

			for (i = 0 ; i < mot.length() ; i++) {
				countAdd = countAdd + Ut.majToIndex(cArray[i]) ;
			}

		if (mot.length() >= 7 ) {
			for ( i = 0 ; i < mot.length() ; i++) {
				eTempo.retire( Ut.majToIndex(cArray[i]) );
			}
			if (eTempo.estVide()==true) {
				scrabble = 50;
			}
		}

		return countMultiply * countAdd + scrabble;
	}

	/**
	* pré-requis : le placement de mot sur this à partir de la case
	* (numLig, numCol) dans le sens donné par sens à l’aide des
	* jetons de e est valide.
	* action/résultat : effectue ce placement et retourne le
	* nombre de jetons retirés de e.
	*/
	public int place(String mot, int numLig, int numCol, char sens, MEE e){
		char[] cArray = mot.toCharArray();
		char[] cPresTab = new char [mot.length()];
		int i; int retire = 0; int index;

		if (sens == 'v') {
			for ( i = 0 ; i<mot.length() ; i++) {
				this.g[numLig+i][numCol].setLettre(cArray[i]);
			}

			for (i=0 ; i < mot.length() ; i++) {
				if (this.g[numLig+i][numCol].estRecouverte()==false) {
					cPresTab[i] = 0 ;
				}
				else {
					cPresTab[i] = this.g[numLig+i][numCol].getLettre();
				}
			}
		}
		else {
			for ( i = 0 ; i<mot.length() ; i++) {
				this.g[numLig][numCol+i].setLettre(cArray[i]) ;
			}

			for (i=0 ; i < mot.length() ; i++) {
				if (this.g[numLig][numCol+i].estRecouverte()==false) {
					cPresTab[i] = 0 ;
				}
				else {
					cPresTab[i] = this.g[numLig+i][numCol].getLettre();
				}
			}		
		}

		for (i = 0 ; i < mot.length() ; i++) {
			if (cPresTab[i] != 0 ) {
				index = Ut.majToIndex(cArray[i]);
				e.ajoute(index);
				retire--;
			}
		}

		for (i = 0 ; i < mot.length() ; i++) {
			index = Ut.majToIndex(cArray[i]);
			e.retire(index);
			retire++;
		}
		
		return retire;
	}

}