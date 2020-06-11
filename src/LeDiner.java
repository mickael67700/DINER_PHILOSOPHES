public class LeDiner {
    public static void main(String[] args) {
        int dim = 7;
        Fourchettes fourchettes = new Fourchettes(dim);
        Philosophe[] mangeurs = new Philosophe[dim];

        ThreadGroup groupe = new ThreadGroup("philos");

        for (int i =0; i<dim; i++)
            mangeurs[i] = new  Philosophe(groupe, i, 4, fourchettes);

        long dateDepart = System.currentTimeMillis();
        for (Philosophe mangeur:mangeurs)  mangeur.start();
        while( groupe.activeCount()!=0) Thread.yield();
        long dateFin = System.currentTimeMillis();
        double duree = (dateFin -  dateDepart) / 1000d;

        System.out.printf("Le repas est fini en %.3f s.", duree);
    }
}
