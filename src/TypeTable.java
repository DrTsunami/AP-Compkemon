
public class TypeTable {
	
	float[][] damageTable;
	
	public TypeTable() {
		
		this.damageTable = new float[6][6];

		damageTable[0][0] = 0.5f;
		damageTable[0][1] = 0.5f;
		damageTable[0][2] = 1.0f;
		damageTable[0][3] = 0.5f;
		damageTable[0][4] = 0.5f;
		damageTable[0][5] = 10.0f;
		damageTable[1][0] = 2.0f;
		damageTable[1][1] = 0.5f;
		damageTable[1][2] = 1.0f;
		damageTable[1][3] = 0.5f;
		damageTable[1][4] = 2.0f;
		damageTable[1][5] = 0.5f;
		damageTable[2][0] = 1.0f;
		damageTable[2][1] = 1.0f;
		damageTable[2][2] = 1.0f;
		damageTable[2][3] = 1.0f;
		damageTable[2][4] = 1.0f;
		damageTable[2][5] = 1.0f;
		damageTable[3][0] = 2.0f;
		damageTable[3][1] = 2.0f;
		damageTable[3][2] = 1.0f;
		damageTable[3][3] = 2.0f;
		damageTable[3][4] = 0.5f;
		damageTable[3][5] = 0.5f;
		damageTable[4][0] = 2.0f;
		damageTable[4][1] = 0.5f;
		damageTable[4][2] = 1.0f;
		damageTable[4][3] = 2.0f;
		damageTable[4][4] = 0.5f;
		damageTable[4][5] = 0.5f;
		damageTable[5][0] = 0.0f;
		damageTable[5][1] = 2.0f;
		damageTable[5][2] = 2.0f;
		damageTable[5][3] = 2.0f;
		damageTable[5][4] = 2.0f;
		damageTable[5][5] = 1.0f;
		
	}
	
	public float getMultiplier(int x, int y) {
		float multiplier = damageTable[x][y];
		return multiplier;
	}
	


	
}
