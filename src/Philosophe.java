import java.util.Random;

public class Philosophe extends Thread{
    /** no du philosophe */
    int no;
    /** nb de bouchees restantes dans l'assiette */
    int nbBouchees;
    /** date de debut du diner */
    long debut;
    /** temps total du repas pour le philosophe*/
    double tempsTotalRepas;

    /** liste de fourchettes */
    Fourchettes lesFourchettes;
    /**temps min d'une bouchee en milliseconde*/
    static final int TempsBaseBouchee=100;
    /**temps max en plus pour une bouchee en milliseconde*/
    static final int TempsBouchee=500;
    /**temps min d'une pensee en milliseconde*/
    static final int TempsMinPensee=100;
    /**temps max en plus pour une pensee en milliseconde*/
    static final int TempsPensee=500;

    Philosophe() {}

    /** initialise le no et nb de bouchees */
    Philosophe(int _no, int _nbBouchees, Fourchettes _lesFourchettes)
    {
        super("philo"+_no);
        no = _no;
        nbBouchees = _nbBouchees;
        lesFourchettes = _lesFourchettes;
    }

    /** initialise le no et nb de bouchees */
    Philosophe(ThreadGroup groupe, int _no, int _nbBouchees, Fourchettes _lesFourchettes)
    {
        super(groupe, "philo"+_no);
        no = _no;
        nbBouchees = _nbBouchees;
        lesFourchettes = _lesFourchettes;
    }

    /** fonction principale du philosophe : cycle sur manger, penser.
     * Pour manger, il prend la fourchette de droite et celle de gauche.
     * Donc i prend la fourchette i et i+1.<br>
     * Le philosophe garde les fourchettes un certains temps et les depose ensuite<br>
     * la boucle se termine lorsque le philosophe a termine ses bouchees.
     * */
    public void run()
    {
        debut = System.currentTimeMillis();
        Random hasard = new Random();
        while(nbBouchees>0)
        {
            System.out.println(this.getName() + " : je demande les fourchettes, j'attends en pensant");
            lesFourchettes.prendre(no);
            nbBouchees--;
            System.out.println(this.getName() + " : j'ai obtenu les fourchettes, je mange, il me reste " + nbBouchees + " bouchees.");
            try {  Thread.sleep(Philosophe.TempsBaseBouchee +  hasard.nextInt(Philosophe.TempsBouchee ));}
            catch (InterruptedException e) {}

            lesFourchettes.deposer(no);
            System.out.println(this.getName() + " : je pense un peu après ma bouchée...");
            try {  Thread.sleep(Philosophe.TempsMinPensee +  hasard.nextInt(Philosophe.TempsPensee ));}
            catch (InterruptedException e) {}
        }
        long fin = System.currentTimeMillis();
        tempsTotalRepas = (fin-debut)/1000d;
        System.out.printf("%s : j'ai fini en %.3f s.\n", this.getName(),tempsTotalRepas);
    }
}

