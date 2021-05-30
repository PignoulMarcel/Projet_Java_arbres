package et3.java.projet.application;

public class AmateurDArbres {
    protected final int id;
    private static Mairie mairie;

    private static int incId = 0;

    public AmateurDArbres(){
        this.id = incId;
        incId += 1;
    }

    protected AmateurDArbres(int id){
        this.id = id;
    }

    public int getId() {
    	return id;
    }

    public void getNotification(Arbre arbre, TypeModif type){
        //TODO peut etre
    }
    
}
