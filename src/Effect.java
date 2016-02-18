
public class Effect {
	
	String name;
		
	public Effect () {		
		this.name = "";
	}
	
	public Effect (String name) {
		this.name = name;
	}
	
	public void Apathy(Compkemon user, Compkemon target, TypeTable typeTable) {
		for (int i = 0; i < typeTable.damageTable.length; i++) {
			typeTable.damageTable[2][i] = 0.1f;
			System.out.println("Hey: " + "Damage table [2][" + i + "] = " + typeTable.damageTable[2][i]);			
		}
		

	}
	
	
}
