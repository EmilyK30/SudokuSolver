// Affiche la grille en console avec des bordures lisibles
public class Affichage {

    public static void afficher(int[][] g) {
        for (int i = 0; i < 9; i++) {
            if (i == 0) System.out.println("╔═══════╤═══════╤═══════╗");
            else if (i % 3 == 0) System.out.println("╠═══════╪═══════╪═══════╣");
            else System.out.println("╟───────┼───────┼───────╢");

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) sb.append("║ ");
                else sb.append("│ ");
                sb.append(g[i][j] == 0 ? "." : g[i][j]).append(" ");
            }
            sb.append("║");
            System.out.println(sb);
        }
        System.out.println("╚═══════╧═══════╧═══════╝\n");
    }
}
