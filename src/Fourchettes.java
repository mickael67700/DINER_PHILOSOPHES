import java.util.Arrays;

/** liste des Fourchettes que doivent se partager les philosophes*/
public class Fourchettes {
    /** tableau d'occupation des fourchettes false = occupee, true = libre*/
    final boolean[] lesFourchettes;
    /** nb de fourchettes*/
    final int taille;

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
        int droite = (no+1)%taille;
        while (!lesFourchettes[no] || !lesFourchettes[droite]) {
            try   {  wait();  } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lesFourchettes[no] = false;
        lesFourchettes[droite] = false;
    }

    /** fonction appelee par un processus philosophe i. <br>
     * libere la fourchette de gauche (i) et de droite (i+1) <br>
     * et reveille les processus en attente sur les fourchettes*/
    public synchronized void deposer(int no) {
        int droite = (no+1)%taille;
        lesFourchettes[no] = true;
        lesFourchettes[droite] = true;
        notifyAll(); // reveille les processus en attente de fourchettes
    }
}
