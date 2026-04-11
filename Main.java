// Point d'entrée du programme
// Usage : java Main grille.txt
//      ou java Main (saisie manuelle)

public class Main {
    public static void main(String[] args) {
        int[][] grille;

        if (args.length > 0) {
            grille = Loader.depuisFichier(args[0]);
        } else {
            grille = Loader.depuisSaisie();
        }

        System.out.println("\nGrille initiale :");
        Affichage.afficher(grille);

        if (Solver.resoudre(grille)) {
            System.out.println("Grille resolue :");
            Affichage.afficher(grille);
        } else {
            System.out.println("Cette grille n'a pas de solution.");
        }
    }
}
