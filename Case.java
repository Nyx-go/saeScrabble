public class Case {
	private int couleur;
	private char lettre;

	/*** pré-requis : uneCouleur est un entier entre 1 et 5* action : constructeur de Case*/
	public Case (int uneCouleur){
		this.couleur=uneCouleur;
	}

	public int getCouleur(){
		return this.couleur;
	}

	public char getLettre(){
		return this.lettre;
	}

	public void setLettre (char let){
	this.lettre=let;	
	}

	public boolean estRecouverte(){
		if(this.getLettre()!='\u0000'){
			return true;
		}
		else{
			return false;
		}
	}

	public String toString(){
		return String.valueOf(this.lettre);
	}
}