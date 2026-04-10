public class SudokuValidator {

/**
* Vérifie si un nombre peut être placé dans une case
*/
public static boolean estValide(Sudoku sudoku, int ligne, int col, int num) {
int[][] grille = sudoku.getGrille();

return !estDansLigne(grille, ligne, num)
&& !estDansColonne(grille, col, num)
&& !estDansBloc(grille, ligne, col, num);
}

/**
* Vérifie si le nombre est déjà dans la ligne
*/
private static boolean estDansLigne(int[][] grille, int ligne, int num) {
for (int col = 0; col < 9; col++) {
if (grille[ligne][col] == num) {
return true;
}
}
return false;
}

/**
* Vérifie si le nombre est déjà dans la colonne
*/
private static boolean estDansColonne(int[][] grille, int col, int num) {
for (int ligne = 0; ligne < 9; ligne++) {
if (grille[ligne][col] == num) {
return true;
}
}
return false;
}

/**
* Vérifie si le nombre est déjà dans le bloc 3x3
*/
private static boolean estDansBloc(int[][] grille, int ligne, int col, int num) {

int debutLigne = ligne - ligne % 3;
int debutCol = col - col % 3;

for (int i = 0; i < 3; i++) {
for (int j = 0; j < 3; j++) {
if (grille[debutLigne + i][debutCol + j] == num) {
return true;
}
}
}
return false;
}
}
