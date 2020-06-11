import java.util.Scanner;
public class LeDiner {
    public static void main(String[] args) {
        System.out.println("Nombre de philosophes: ");
        int dim = new Scanner(System.in).nextInt();

        Fourchettes fourchettes = new Fourchettes(dim);
        Philosophe[] mangeurs = new Philosophe[dim];

        ThreadGroup groupe = new ThreadGroup("philos");

        for (int i =0; i<dim; i++)
            mangeurs[i] = new  Philosophe(groupe, i, fourchettes);

        long dateDepart = System.currentTimeMillis();
        for (Philosophe mangeur:mangeurs)  mangeur.start();
        while( groupe.activeCount()!=0) Thread.yield();
        long dateFin = System.currentTimeMillis();
        double duree = (dateFin -  dateDepart) / 1000d;

        System.out.printf("Le repas est fini en %.3f s.", duree);
    }
}
