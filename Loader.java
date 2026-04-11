import java.io.*;
import java.util.Scanner;

// Charge la grille depuis un fichier texte ou la saisie clavier
public class Loader {

    // Lit la grille depuis un fichier (9 lignes de 9 chiffres séparés par des espaces)
    public static int[][] depuisFichier(String chemin) {
        int[][] grille = new int[9][9];
        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {
            for (int i = 0; i < 9; i++) {
                String ligne = br.readLine();
                if (ligne == null) {
                    System.err.println("Erreur : le fichier a moins de 9 lignes.");
                    System.exit(1);
                }
                String[] parts = ligne.trim().split("\\s+");
                if (parts.length != 9) {
                    System.err.println("Erreur ligne " + (i+1) + " : il faut 9 valeurs.");
                    System.exit(1);
                }
                for (int j = 0; j < 9; j++) {
                    grille[i][j] = Integer.parseInt(parts[j]);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erreur : fichier introuvable -> " + chemin);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Erreur : valeur non numerique dans le fichier.");
            System.exit(1);
        }
        return grille;
    }

    // Demande à l'utilisateur de saisir la grille ligne par ligne
    public static int[][] depuisSaisie() {
        int[][] grille = new int[9][9];
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisissez 9 lignes de 9 chiffres (0 = case vide), ex: 5 3 0 0 7 0 0 0 0");
        for (int i = 0; i < 9; i++) {
            System.out.print("Ligne " + (i+1) + " : ");
            String[] parts = sc.nextLine().trim().split("\\s+");
            for (int j = 0; j < 9; j++) {
                grille[i][j] = Integer.parseInt(parts[j]);
            }
        }
        return grille;
    }
}
