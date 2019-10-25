package enums;

public enum WaterHeaterType {
	ELECTRIC (1),
	GAS (2);
	
	private final int type;
	
	private WaterHeaterType(int type) {
		this.type = type;
	}
	
	public int getValue() {
		return this.type;
	}
}