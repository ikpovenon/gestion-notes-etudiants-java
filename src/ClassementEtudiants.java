import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Classe responsable du tri des étudiants selon leur moyenne
public class ClassementEtudiants {

    // Trie la liste par moyenne décroissante
    public static void trierParMoyenneDecroissante(List<Etudiant> etudiants) {
        Collections.sort(etudiants, new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant e1, Etudiant e2) {
                return Double.compare(e2.getMoyenne(), e1.getMoyenne());
            }
        });
    }
}