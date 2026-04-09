public class Main {

    public static void main(String[] args) {

        Sudoku sudoku;

        try {
            // 🔹 Si un fichier est donné en argument
            if (args.length > 0) {
                sudoku = SudokuLoader.chargerDepuisFichier(args[0]);
            } else {
                // 🔹 Sinon utiliser grille.txt par défaut
                sudoku = SudokuLoader.chargerDepuisFichier("grille.txt");
            }

        } catch (Exception e) {
            System.out.println(" Erreur lors du chargement du fichier !");
            System.out.println("Vérifiez que le fichier existe et est bien formaté.");
            return;
        }

        // 🔹 Affichage initial
        System.out.println("\nGrille initiale :");
        SudokuPrinter.afficher(sudoku);

        // 🔹 Résolution
        System.out.println("\nRésolution en cours...");
        boolean solved = SudokuSolver.solve(sudoku);

        // 🔹 Résultat
        if (solved) {
            System.out.println("\nGrille résolue :");
            SudokuPrinter.afficher(sudoku);
        } else {
            System.out.println("\n Aucune solution trouvée !");
        }
    }
}
