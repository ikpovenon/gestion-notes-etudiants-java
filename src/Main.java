import java.io.IOException;
import java.util.List;

// Point d'entrée du programme
// Orchestration : lecture, calcul, tri, affichage, sauvegarde
public class Main {
    public static void main(String[] args) {
        String fichierEntree = "data/etudiants.csv";
        String fichierSortie = "output/resultats.csv";

        try {
            List<Etudiant> etudiants = GestionCSV.lireEtudiantsDepuisCSV(fichierEntree);

            if (etudiants.isEmpty()) {
                System.out.println("Aucun étudiant valide trouvé dans le fichier.");
                return;
            }

            for (Etudiant etudiant : etudiants) {
                etudiant.calculerMoyenne();
            }

            ClassementEtudiants.trierParMoyenneDecroissante(etudiants);

            System.out.println("=== CLASSEMENT DES ÉTUDIANTS ===");
            int rang = 1;
            for (Etudiant etudiant : etudiants) {
                System.out.println(rang + ". " + etudiant);
                rang++;
            }

            GestionResultats.sauvegarderResultatsCSV(etudiants, fichierSortie);
            System.out.println("\nRésultats sauvegardés dans : " + fichierSortie);

        } catch (IOException e) {
            System.out.println("Erreur de lecture/écriture de fichier : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur inattendue : " + e.getMessage());
        }
    }
}