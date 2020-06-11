import java.util.Arrays;

/** liste des Fourchettes que doivent se partager les philosophes*/
public class Fourchettes {
    /** tableau d'occupation des fourchettes false = occupee, true = libre*/
    boolean[] lesFourchettes;
    /** nb de fourchettes*/
    int taille;

    /** constructeur initialisant la taille et le tableau des fourchettes a true*/
    public Fourchettes(int _taille) {
        taille = _taille;
        lesFourchettes = new boolean[taille];
        Arrays.fill(lesFourchettes, true);
    }

    /** fonction appelee par un processus philosophe i. <br>
     * Si la fourchette de gauche (i) et de droite (i+1) est libre alors le philosophe les prend,
     * sinon, il est mis en attente*/
    public synchronized void prendre(int no) {
        int gauche = no;
        int droite = (no+1)%taille;
        while (!lesFourchettes[gauche] || !lesFourchettes[droite]) {
            try   {  wait();  } catch (InterruptedException e) {}
        }
        lesFourchettes[gauche] = false;
        lesFourchettes[droite] = false;
    }

    /** fonction appelee par un processus philosophe i. <br>
     * libere la fourchette de gauche (i) et de droite (i+1) <br>
     * et reveille les processus en attente sur les fourchettes*/
    public synchronized void deposer(int no) {
        int gauche = no;
        int droite = (no+1)%taille;
        lesFourchettes[gauche] = true;
        lesFourchettes[droite] = true;
        notifyAll(); // reveille les processus en attente de fourchettes
    }
}
