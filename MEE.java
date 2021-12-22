public class MEE {
	private int [] tabFreq;  // tabFreq[i] est le nombre d’exemplaires
							// (fréquence) de l’élément i
	private int nbTotEx;// nombre total d’exemplaires




	/**
	 *  pré-requis : max >= 0
	 *  action : crée un multi-ensemble vide dont les éléments seront
	 * inférieurs à max*/
	public MEE (int max){
		this.tabFreq= new int[max];
		this.nbTotEx=0;
	}

	/***  pré-requis : les éléments de tab sont positifs ou nuls
	 *   action : crée un multi-ensemble dont le tableau de fréquences est
	 * .une copie de tab*/
	public MEE (int[] tab){
		this.nbTotEx = 0;
		this.tabFreq = new int [tab.length];

		for (i=0 ; i < tab.length ; i++) {
			this.tabFreq[i]=tab[i];
			if (tab[i]!=0) {
				this.nbTotEx[i] = this.nbTotEx + tab[i] ;
			}
		}
	}

	/*** constructeur par copie*/
	public MEE (MEE e){
	this.tabFreq=new int[e.length];
		this.nbTotEx=e.nbTotEx;
		for(i=0;i<e.length;i++){
		this.tabFreq[i]=e.nbTotEx[i];
	}


	/***  résultat : vrai ssi cet ensemble est vide*/
	public boolean estVide (){
		boolean vide ;

		if {this.nbTotEx==0} {
			vide = true ;
		}
		else {
			vide = false ;
		}
		return vide;
	}

	
	/*** pré-requis : 0 <= i < tabFreq.length
	 *  action : ajoute un exemplaire de i à this*/
	public void ajoute (int i) {
		this.tabFreq[i-1] = this.tabFreq[i-1]+1 ;
		this.nbTotEx = this.nbTotEx + 1;
	}

	/*** pré-requis : 0 <= i < tabFreq.length
	 *  action/résultat : retire un exemplaire de i de this s’il en existe,
	 *  et retourne vrai ssi cette action a pu être effectuée*/
	public boolean retire (int i) {
		if(i!=0){
			this.tabFreq.length[i]=i-1;
			this.nbTotEx=this.nbTotEx-1;
			return true;
		}else{
			return false;
		}
	}

	/*** pré-requis : this est non vide
	 * action/résultat : retire de this un exemplaire choisi aléatoirement
	 * et le retourne*/
	public int retireAleat () {
		boolean worked = false;

		while (worked==false) {
			int i = Ut.randomMinMax(0,this.length-1);
			worked = this.retire(i);
		}
		return i;
	}

	/*** pré-requis : 0 <= i < tabFreq.length
	 * action/résultat : transfère un exemplaire de i de this vers e s’il
	 * en existe, et retourne vrai ssi cette action a pu être effectuée*/
	public boolean transfere (MEE e, int i) {
		boolean workedReti , workedAjout ;

		workedAjout = false ;
		workedReti = this.retire(i) ;
		if (workedReti == true && i < this.tabFreq.length) {
			e.ajoute(i);
			workedAjout == true ;
		}
		return workedAjout;
	}

	/** pré-requis : k >= 0
	 * action : tranfère k exemplaires choisis aléatoirement de this vers e
	 * dans la limite du contenu de this*  résultat : le nombre d’exemplaires effectivement transférés*/
	public int transfereAleat (MEE e, int k) {
		boolean worked;
		int x = k;

		while (x<0 && this.nbTotEx>0) {
			int i = Ut.randomMinMax(0,this.length-1);
			worked = this.transfere(e,i);
			if (worked == true) {
				x=x-1;
			}
		}
		return k-x;
		
	}

	/*** pré-requis : tabFreq.length <= v.length
	 *  résultat : retourne la somme des valeurs des exemplaires des
	 * éléments de this, la valeur d’un exemplaire d’un élément i
	 * de this étant égale à v[i]*/
	public int sommeValeurs (int[] v){
		int x = 0;

		for (i=0 ; i<tabFreq.length ; i++) {
			x = x + tabFreq[i] * v[i];
		}
		return x;
	}
}
