import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Classe responsable de la lecture du fichier CSV
public class GestionCSV {

    // Lit le fichier CSV et construit une liste d'objets Etudiant
    public static List<Etudiant> lireEtudiantsDepuisCSV(String nomFichier) throws IOException {
        List<Etudiant> etudiants = new ArrayList<>();

        BufferedReader lecteur = new BufferedReader(new FileReader(nomFichier));
        String ligneEntete = lecteur.readLine();

        if (ligneEntete == null || ligneEntete.trim().isEmpty()) {
            lecteur.close();
            return etudiants;
        }

        String[] entetes = ligneEntete.split(",");

        if (entetes.length < 3) {
            lecteur.close();
            throw new IOException("Le fichier CSV doit contenir au moins : id, nom et une UA.");
        }

        String ligne;
        while ((ligne = lecteur.readLine()) != null) {
            if (ligne.trim().isEmpty()) {
                continue;
            }

            String[] parties = ligne.split(",");

            if (parties.length != entetes.length) {
                System.out.println("Ligne ignorée (nombre de colonnes invalide) : " + ligne);
                continue;
            }

            String idTexte = parties[0].trim();
            String nom = parties[1].trim();

            if (!ValidationUtils.estEntierValide(idTexte)) {
                System.out.println("ID invalide, ligne ignorée : " + ligne);
                continue;
            }

            if (!ValidationUtils.estNomValide(nom)) {
                System.out.println("Nom invalide, ligne ignorée : " + ligne);
                continue;
            }

            int id = Integer.parseInt(idTexte);
            Etudiant etudiant = new Etudiant(id, nom);

            boolean ligneValide = true;

            // Lecture des notes associées aux colonnes UA1, UA2, UA3, etc.
            for (int i = 2; i < entetes.length; i++) {
                String nomUA = entetes[i].trim();
                String noteTexte = parties[i].trim();

                if (!ValidationUtils.estDoubleValide(noteTexte)) {
                    System.out.println("Note invalide, ligne ignorée : " + ligne);
                    ligneValide = false;
                    break;
                }

                double note = Double.parseDouble(noteTexte);

                if (!ValidationUtils.estNomUAValide(nomUA)) {
                    System.out.println("Nom d'UA invalide dans l'entête : " + nomUA);
                    ligneValide = false;
                    break;
                }

                if (!ValidationUtils.estNoteValide(note)) {
                    System.out.println("Note hors intervalle, ligne ignorée : " + ligne);
                    ligneValide = false;
                    break;
                }

                etudiant.ajouterNoteUA(nomUA, note);
            }

            if (ligneValide) {
                etudiants.add(etudiant);
            }
        }

        lecteur.close();
        return etudiants;
    }
}