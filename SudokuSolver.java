public class SudokuSolver {

    // Méthode principale appelée par le Main
    public static boolean solve(Sudoku sudoku) {

        int[][] grille = sudoku.getGrille();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                // Si case vide
                if (grille[row][col] == 0) {

                    // Tester les chiffres de 1 à 9
                    for (int num = 1; num <= 9; num++) {

                        if (isValid(grille, row, col, num)) {

                            grille[row][col] = num;

                            // Appel récursif
                            if (solve(sudoku)) {
                                return true;
                            }

                            // BACKTRACK (annuler)
                            grille[row][col] = 0;
                        }
                    }

                    // Aucun chiffre ne fonctionne
                    return false;
                }
            }
        }

        // Grille remplie
        return true;
    }

    // Vérifie si un nombre peut être placé
    private static boolean isValid(int[][] grille, int row, int col, int num) {

        // Vérification ligne
        for (int i = 0; i < 9; i++) {
            if (grille[row][i] == num) {
                return false;
            }
        }

        // Vérification colonne
        for (int i = 0; i < 9; i++) {
            if (grille[i][col] == num) {
                return false;
            }
        }

        // Vérification bloc 3x3
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (grille[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
