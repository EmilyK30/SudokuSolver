// Résout la grille de Sudoku par backtracking (essai-erreur récursif)
public class Solver {

    // Retourne true si la grille a été résolue, false sinon
    // La grille est modifiée directement (remplie avec la solution)
    public static boolean resoudre(int[][] g) {
        // Chercher la première case vide
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (g[i][j] == 0) {
                    // Tester les chiffres 1 à 9
                    for (int n = 1; n <= 9; n++) {
                        if (valide(g, i, j, n)) {
                            g[i][j] = n;
                            if (resoudre(g)) return true; // récursion
                            g[i][j] = 0; // backtrack
                        }
                    }
                    return false; // aucun chiffre ne fonctionne
                }
            }
        }
        return true; // plus de case vide = grille complète
    }

    // Vérifie qu'on peut placer n en (ligne, col) sans conflit
    private static boolean valide(int[][] g, int ligne, int col, int n) {
        // Vérifier la ligne
        for (int j = 0; j < 9; j++)
            if (g[ligne][j] == n) return false;

        // Vérifier la colonne
        for (int i = 0; i < 9; i++)
            if (g[i][col] == n) return false;

        // Vérifier le bloc 3x3
        int di = (ligne / 3) * 3;
        int dj = (col / 3) * 3;
        for (int i = di; i < di + 3; i++)
            for (int j = dj; j < dj + 3; j++)
                if (g[i][j] == n) return false;

        return true;
    }
}
