/**
 * SudokuPrinter.java
 * Classe responsable de l'affichage d'une grille de Sudoku en mode console.
 * Utilise les caractères spéciaux de dessin de boîte (box-drawing characters Unicode)
 * pour un rendu structuré et lisible, conforme aux exemples du sujet.
 *
 * Auteur : Groupe Gx - L3 GLSI
 */
public class SudokuPrinter {

    // ─── Caractères Unicode de dessin de boîte ────────────────────────────────

    // Coins
    private static final String COIN_HG  = "╔"; // Coin Haut-Gauche
    private static final String COIN_HD  = "╗"; // Coin Haut-Droit
    private static final String COIN_BG  = "╚"; // Coin Bas-Gauche
    private static final String COIN_BD  = "╝"; // Coin Bas-Droit

    // Jonctions bords externes
    private static final String T_HAUT   = "╦"; // T vers le bas (bord haut)
    private static final String T_BAS    = "╩"; // T vers le haut (bord bas)
    private static final String T_GAUCHE = "╠"; // T vers la droite (bord gauche)
    private static final String T_DROITE = "╣"; // T vers la gauche (bord droit)

    // Jonctions internes (séparateurs de sous-grilles 3x3)
    private static final String CROIX_EP = "╬"; // Croisement épais interne
    private static final String T_H_EP   = "╤"; // T haut épais (séparateur col interne/bord haut)
    private static final String T_B_EP   = "╧"; // T bas épais
    private static final String T_G_EP   = "╟"; // T gauche fin (séparateur ligne interne)
    private static final String T_D_EP   = "╢"; // T droit fin
    private static final String CROIX_FN = "┼"; // Croisement fin interne

    // Lignes
    private static final String LIGNE_EP = "═"; // Ligne horizontale épaisse
    private static final String LIGNE_FN = "─"; // Ligne horizontale fine
    private static final String COL_EP   = "║"; // Colonne verticale épaisse
    private static final String COL_FN   = "│"; // Colonne verticale fine

    // ─── Méthode principale d'affichage ──────────────────────────────────────

    /**
     * Affiche une grille de Sudoku encapsulée dans un objet Sudoku.
     * Les cases vides (valeur 0) sont représentées par un espace.
     *
     * @param sudoku L'objet Sudoku contenant la grille à afficher
     */
    public static void afficher(Sudoku sudoku) {
        int[][] grille = sudoku.getGrille();
        afficherGrille(grille);
    }

    /**
     * Affiche directement un tableau 2D représentant une grille de Sudoku.
     *
     * @param grille Tableau 9x9 de valeurs (0 = case vide)
     */
    public static void afficherGrille(int[][] grille) {
        // Ligne du haut
        System.out.println(construireLigneHorizontale("haut"));

        for (int i = 0; i < 9; i++) {
            // Séparateur horizontal entre blocs 3x3 (sauf la première ligne déjà gérée)
            if (i > 0 && i % 3 == 0) {
                System.out.println(construireLigneHorizontale("milieu_epais"));
            } else if (i > 0) {
                System.out.println(construireLigneHorizontale("milieu_fin"));
            }

            // Ligne de données
            System.out.println(construireLigneDonnees(grille[i]));
        }

        // Ligne du bas
        System.out.println(construireLigneHorizontale("bas"));
    }

    // ─── Méthodes privées de construction ────────────────────────────────────

    /**
     * Construit une ligne horizontale de séparation selon le type demandé.
     *
     * @param type "haut", "bas", "milieu_epais" (entre blocs 3x3), "milieu_fin" (entre cases)
     * @return La chaîne de caractères représentant la ligne
     */
    private static String construireLigneHorizontale(String type) {
        String gauche, droite, tGauche, tDroite, croix, ligne;

        switch (type) {
            case "haut":
                gauche  = COIN_HG;
                droite  = COIN_HD;
                tGauche = T_HAUT;   // jonction externe haut inutilisée ici, on utilise T_HAUT
                tDroite = T_HAUT;
                croix   = T_HAUT;
                ligne   = LIGNE_EP;
                // Structure : ╔═══╤═══╤═══╦═══╤═══╤═══╦═══╤═══╤═══╗
                return gauche
                        + segment(ligne, 3) + croix
                        + segment(ligne, 3) + croix
                        + segment(ligne, 3) + T_HAUT
                        + segment(ligne, 3) + croix
                        + segment(ligne, 3) + croix
                        + segment(ligne, 3) + T_HAUT
                        + segment(ligne, 3) + croix
                        + segment(ligne, 3) + croix
                        + segment(ligne, 3)
                        + droite;

            case "bas":
                // Structure : ╚═══╧═══╧═══╩═══╧═══╧═══╩═══╧═══╧═══╝
                return COIN_BG
                        + segment(LIGNE_EP, 3) + T_BAS
                        + segment(LIGNE_EP, 3) + T_BAS
                        + segment(LIGNE_EP, 3) + T_B_EP
                        + segment(LIGNE_EP, 3) + T_BAS
                        + segment(LIGNE_EP, 3) + T_BAS
                        + segment(LIGNE_EP, 3) + T_B_EP
                        + segment(LIGNE_EP, 3) + T_BAS
                        + segment(LIGNE_EP, 3) + T_BAS
                        + segment(LIGNE_EP, 3)
                        + COIN_BD;

            case "milieu_epais":
                // Séparateur épais entre blocs 3x3
                // Structure : ╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣
                return T_GAUCHE
                        + segment(LIGNE_EP, 3) + CROIX_EP
                        + segment(LIGNE_EP, 3) + CROIX_EP
                        + segment(LIGNE_EP, 3) + CROIX_EP
                        + segment(LIGNE_EP, 3) + CROIX_EP
                        + segment(LIGNE_EP, 3) + CROIX_EP
                        + segment(LIGNE_EP, 3) + CROIX_EP
                        + segment(LIGNE_EP, 3) + CROIX_EP
                        + segment(LIGNE_EP, 3) + CROIX_EP
                        + segment(LIGNE_EP, 3)
                        + T_DROITE;

            case "milieu_fin":
            default:
                // Séparateur fin entre deux lignes de cases dans le même bloc
                // Structure : ╟───┼───┼───╫───┼───┼───╫───┼───┼───╢
                return T_G_EP
                        + segment(LIGNE_FN, 3) + CROIX_FN
                        + segment(LIGNE_FN, 3) + CROIX_FN
                        + segment(LIGNE_FN, 3) + "╫"
                        + segment(LIGNE_FN, 3) + CROIX_FN
                        + segment(LIGNE_FN, 3) + CROIX_FN
                        + segment(LIGNE_FN, 3) + "╫"
                        + segment(LIGNE_FN, 3) + CROIX_FN
                        + segment(LIGNE_FN, 3) + CROIX_FN
                        + segment(LIGNE_FN, 3)
                        + T_D_EP;
        }
    }

    /**
     * Construit une ligne de données (une rangée de la grille).
     * Les séparateurs de colonnes sont épais (║) entre blocs et fins (│) entre cases.
     *
     * @param ligne Tableau de 9 entiers représentant une rangée
     * @return La chaîne formatée avec séparateurs et valeurs
     */
    private static String construireLigneDonnees(int[] ligne) {
        StringBuilder sb = new StringBuilder();
        sb.append(COL_EP); // bord gauche épais

        for (int j = 0; j < 9; j++) {
            // Affiche la valeur ou un espace si case vide
            String valeur = (ligne[j] == 0) ? " " : String.valueOf(ligne[j]);
            sb.append(" ").append(valeur).append(" ");

            // Séparateur de colonne
            if (j == 8) {
                sb.append(COL_EP); // bord droit épais
            } else if ((j + 1) % 3 == 0) {
                sb.append(COL_EP); // séparateur de bloc épais
            } else {
                sb.append(COL_FN); // séparateur de case fin
            }
        }

        return sb.toString();
    }

    /**
     * Répète un caractère (ou chaîne) n fois.
     *
     * @param caractere Le caractère à répéter
     * @param n         Le nombre de répétitions
     * @return La chaîne obtenue
     */
    private static String segment(String caractere, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(caractere);
        }
        return sb.toString();
    }
}
