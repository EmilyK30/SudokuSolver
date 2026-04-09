/**
 * Solveur de Sudoku optimisé avec backtracking et tableaux de contraintes
 * Complexité : O(9^(n)) dans le pire cas, mais très rapide en pratique
 * 
 * @author Groupe Sudoku - Étudiant 3
 * @version 2.0
 */
public class SudokuSolver {
    
    // Tableaux auxiliaires pour optimiser les vérifications (O(1))
    private static boolean[][] lignes = new boolean[9][10];
    private static boolean[][] colonnes = new boolean[9][10];
    private static boolean[][] blocs = new boolean[9][10];
    private static boolean[][] casesFixes = new boolean[9][9];
    
    // Compteurs pour les statistiques
    private static int recursiveCalls = 0;
    private static int backtrackCount = 0;
    
    /**
     * Méthode principale de résolution
     * @param sudoku La grille à résoudre
     * @return true si une solution existe
     */
    public static boolean solve(Sudoku sudoku) {
        // Réinitialisation
        recursiveCalls = 0;
        backtrackCount = 0;
        initialiserTableaux();
        
        int[][] grille = sudoku.getGrille();
        
        // Marquer les cases fixes (cellules initiales à ne pas modifier)
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                casesFixes[i][j] = (grille[i][j] != 0);
            }
        }
        
        // Initialiser les contraintes
        initialiserContraintes(grille);
        
        // Chronométrage
        long startTime = System.currentTimeMillis();
        boolean result = backtrack(grille, 0, 0);
        long endTime = System.currentTimeMillis();
        
        // Affichage des statistiques
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     STATISTIQUES DE RÉSOLUTION     ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.printf("║ Temps : %28d ms ║\n", (endTime - startTime));
        System.out.printf("║ Appels récursifs : %19d ║\n", recursiveCalls);
        System.out.printf("║ Backtracks : %24d ║\n", backtrackCount);
        System.out.println("╚════════════════════════════════════╝");
        
        return result;
    }
    
    /**
     * Backtracking optimisé avec parcours séquentiel
     */
    private static boolean backtrack(int[][] grille, int row, int col) {
        recursiveCalls++;
        
        // Fin de la grille ?
        if (row == 9) {
            return true;
        }
        
        // Calcul des coordonnées suivantes
        int nextRow = (col == 8) ? row + 1 : row;
        int nextCol = (col + 1) % 9;
        
        // Si case fixe ou déjà remplie → passer à la suivante
        if (casesFixes[row][col] || grille[row][col] != 0) {
            return backtrack(grille, nextRow, nextCol);
        }
        
        int blocIndex = (row / 3) * 3 + (col / 3);
        
        // Essayer les chiffres de 1 à 9
        for (int num = 1; num <= 9; num++) {
            if (!lignes[row][num] && !colonnes[col][num] && !blocs[blocIndex][num]) {
                
                // Placement
                grille[row][col] = num;
                lignes[row][num] = colonnes[col][num] = blocs[blocIndex][num] = true;
                
                // Appel récursif
                if (backtrack(grille, nextRow, nextCol)) {
                    return true;
                }
                
                // BACKTRACK
                backtrackCount++;
                grille[row][col] = 0;
                lignes[row][num] = colonnes[col][num] = blocs[blocIndex][num] = false;
            }
        }
        
        return false;
    }
    
    /**
     * Initialise les contraintes à partir de la grille donnée
     */
    private static void initialiserContraintes(int[][] grille) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = grille[i][j];
                if (num != 0) {
                    int blocIndex = (i / 3) * 3 + (j / 3);
                    lignes[i][num] = true;
                    colonnes[j][num] = true;
                    blocs[blocIndex][num] = true;
                }
            }
        }
    }
    
    /**
     * Réinitialise tous les tableaux
     */
    private static void initialiserTableaux() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <= 9; j++) {
                lignes[i][j] = false;
                colonnes[i][j] = false;
                blocs[i][j] = false;
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                casesFixes[i][j] = false;
            }
        }
    }
    
    // Getters pour les statistiques
    public static int getRecursiveCalls() { return recursiveCalls; }
    public static int getBacktrackCount() { return backtrackCount; }
}
