import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// Classe responsable de la sauvegarde des résultats dans un fichier CSV
public class GestionResultats {

    public static void sauvegarderResultatsCSV(List<Etudiant> etudiants, String nomFichier) throws IOException {
        File fichier = new File(nomFichier);
        File dossierParent = fichier.getParentFile();

        if (dossierParent != null && !dossierParent.exists()) {
            dossierParent.mkdirs();
        }

        BufferedWriter ecrivain = new BufferedWriter(new FileWriter(fichier));

        ecrivain.write("rang,id,nom,moyenne");
        ecrivain.newLine();

        int rang = 1;
        for (Etudiant etudiant : etudiants) {
            ecrivain.write(
                    rang + "," +
                            etudiant.getId() + "," +
                            etudiant.getNom() + "," +
                            String.format("%.2f", etudiant.getMoyenne())
            );
            ecrivain.newLine();
            rang++;
        }

        ecrivain.close();
    }
}