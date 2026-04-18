import java.util.LinkedHashMap;
import java.util.Map;

// Classe représentant un étudiant avec ses notes par UA et sa moyenne
public class Etudiant {
    private int id;
    private String nom;
    private Map<String, Double> notesParUA;
    private double moyenne;

    public Etudiant(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.notesParUA = new LinkedHashMap<>();
        this.moyenne = 0.0;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Map<String, Double> getNotesParUA() {
        return notesParUA;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void ajouterNoteUA(String ua, double note) {
        notesParUA.put(ua, note);
    }

    // Calcule la moyenne générale des notes de l'étudiant
    public void calculerMoyenne() {
        if (notesParUA.isEmpty()) {
            moyenne = 0.0;
            return;
        }

        double somme = 0.0;
        for (double note : notesParUA.values()) {
            somme += note;
        }

        moyenne = somme / notesParUA.size();
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", notesParUA=" + notesParUA +
                ", moyenne=" + String.format("%.2f", moyenne) +
                '}';
    }
}