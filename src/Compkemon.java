public class Compkemon {

    String name;
    String type;
    Move[] moveset;
    int health;
    int attack;
    int defense;
    int speed;
    
    CompkemonList compkemonList;
    

    public Compkemon(CompkemonList compkemon) {
    	
    	compkemonList = compkemon;
    	
    	switch(compkemon) {
			case Prototype: 
				this.name = "Prototype";
		        this.type = "Normal";
		        this.health = 80;
		        this.attack = 80;
		        this.defense = 80;
		        this.speed = 80; 
		        this.moveset = new Move[4];
		
		        moveset[0] = new Move(MoveName.Tackle);
		        moveset[1] = new Move(MoveName.Growl);
		        moveset[2] = new Move(MoveName.SwordsDance);
		        break;
    	}

    }
    
    public String getMoveset() {
    	String moves = "";
    	for (Move move : moveset) {
    		moves += move + "\t";
    	}
    	return moves;
    }
    
    public String toString() {
        String output = new String();
        output = this.name;
        return output;
    }

}