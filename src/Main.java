import java.util.List;
import java.io.*;
import java.util.*;

public class Main {
	// TODOs 15 : public static List<Personne> lireFichier(String fichier) 
    // TODO 16: public static void ecrireFichier(List<Personne> personnes, String fichier)
    public static class Personne {
        private String nom;
        private int age;
        private String courriel;
        public Personne(String nom, int age, String courriel) {
            this.nom = nom;
            this.age = age;
            this.courriel = courriel;
        }
        public String getNom() {
            return nom;
        }
        public int getAge() {
            return age;
        }
        public String getCourriel() {
            return courriel;
        }
    }

    public static List<Main.Personne> lireFichier(String fichier) {
        List<Personne> personnes = new ArrayList<>();
        try {
            File fichierALire = new File(fichier);
            Scanner scan = new Scanner(fichierALire);
            while (scan.hasNextLine()) {
                String ligne = scan.nextLine();
                String[] elements = ligne.split(",");
                String nom = elements[0];
                int age = Integer.parseInt(elements[1]);
                String email = elements[2];
                Personne personne = new Personne(nom, age, email);
                personnes.add(personne);
            }
            scan.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return personnes;
    }
    public static void ecrireFichier(List<Personne> personnes, String fichierSortie) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierSortie))) {
            writer.write("<html><body>\n");
            for (Personne personne : personnes) {
                writer.write("<p>\n");
                writer.write("Nom: <strong>" + personne.getNom() + "</strong><br>\n");
                writer.write("Age: " + personne.getAge() + "<br>\n");
                writer.write("Email: <a href=\"mailto:" + personne.getCourriel() + "\">" + personne.getCourriel() + "</a><br>\n");
                writer.write("</p>\n");
            }
            writer.write("</body></html>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
       
 
    public static void main(String[] args) {
        // TODOs 11 à 14
        new CalculatriceSwing(); // démarre une calculatrice dont le fonctionnement et l'interface
                            // sont expliqués dans un clip: https://www.loom.com/share/b92c35aead684f3f872197a0558cb99a
       
        String fichier = "entrees.txt";  // fichier fourni
        // TODO 15
        List<Personne> personnes = lireFichier(fichier); // la classe Personne est fournie
        System.out.println("Données extraites du fichier :");
        for (Personne person : personnes) {
            System.out.println("Nom : " + person.getNom());
            System.out.println("Âge : " + person.getAge());
            System.out.println("Courriel : " + person.getCourriel());
            System.out.println();
        }
        // TODO 16
        ecrireFichier(personnes, "sorties.html"); // exemple fourni
    }
}
