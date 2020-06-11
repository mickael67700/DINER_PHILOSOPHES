import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class LeDiner {
    public static void main(String[] args) {
        System.out.println("Nombre de philosophes: ");
        int dim = new Scanner(System.in).nextInt();

        Fourchettes fourchettes = new Fourchettes(dim);
        Philosophe[] mangeurs;

        ThreadGroup groupe = new ThreadGroup("philos");

        mangeurs = IntStream.range(0, dim).mapToObj(i -> new Philosophe(groupe, i, fourchettes)).toArray(Philosophe[]::new);

        long dateDepart = System.currentTimeMillis();
        Arrays.stream(mangeurs).forEach(Thread::start);
        while( groupe.activeCount()!=0) Thread.yield();
        long dateFin = System.currentTimeMillis();
        double duree = (dateFin -  dateDepart) / 1000d;

        System.out.printf("Le repas est fini en %.3f s.", duree);
    }
}
