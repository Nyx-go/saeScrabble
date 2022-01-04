public class joueur{
  private String nom;
  private MEE chevalet;
  private int score;

public joueur(String unNom){
    this.nom=unNom;
}
public void ajouteScore(int nb){
  this.score=this.score+nb;
}
public int nbPointsChevalet (int[] nbPointsJet){
}
public void prendJetons (Mee s, int nbJetons){
}
public int joue (Plateau p, MEE s, int[] nbPointsJet){
}
public boolean joueMot(Plateau p, MEE s, int[] nbPointsJet) {
}
public void joueMotAux (Plateau p, MEE s, int[] nbPointsJet, String mot, int numLig, int numCol, char sens){
}
public void echangeJetons(MEE sac){
}
public boolean estCorrectPourEchange (String mot){
}
public void echangeJetonsAux(MEE sac, String ensJetons){
}