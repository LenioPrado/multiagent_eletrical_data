package enums;

public enum AcclimatizationType {
	AIR_CONDITIONER (1),
	FURNACE (2);
	
	private final int type;
	
	private AcclimatizationType(int type) {
		this.type = type;
	}
	
	public int getValue() {
		return this.type;
	}
}