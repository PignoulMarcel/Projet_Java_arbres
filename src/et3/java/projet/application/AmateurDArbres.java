package et3.java.projet.application;

public class AmateurDArbres {
    public final int id;
    private static Mairie maire;

    private static int incId = 0;

    public AmateurDArbres(){
        this.id = incId;
        incId += 1;
    }

}
