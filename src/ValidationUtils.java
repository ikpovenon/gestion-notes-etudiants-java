// Classe utilitaire pour valider les données du projet
public class ValidationUtils {

    public static boolean estEntierValide(String valeur) {
        try {
            Integer.parseInt(valeur);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean estDoubleValide(String valeur) {
        try {
            Double.parseDouble(valeur);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean estNomValide(String nom) {
        return nom != null && !nom.trim().isEmpty();
    }

    public static boolean estNomUAValide(String nomUA) {
        return nomUA != null && nomUA.matches("UA\\d+");
    }

    // Adapter ici la borne maximale selon votre système de notation
    public static boolean estNoteValide(double note) {
        return note >= 0 && note <= 20;
    }
}