package et3.java.projet.application;

public class AmateurDArbres {
    protected final int id;
    private static Mairie mairie;
    private float fonds = 0;
    private static int incId = 0;

    public AmateurDArbres(){
        this.id = incId;
        incId += 1;
    }
    
    public float getFonds(){
    	return fonds;
    }
    public void setFonds(float fonds) {
    	this.fonds = fonds;
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
