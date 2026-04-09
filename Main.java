public class Main {

    public static void main(String[] args) {

        Sudoku sudoku = SudokuLoader.chargerDepuisFichier("grille.txt");

        System.out.println("Grille initiale :");
        SudokuPrinter.afficher(sudoku);

        if (SudokuSolver.solve(sudoku)) {
            System.out.println("\nGrille résolue :");
            SudokuPrinter.afficher(sudoku);
        } else {
            System.out.println("Aucune solution trouvée !");
        }
    }
}
