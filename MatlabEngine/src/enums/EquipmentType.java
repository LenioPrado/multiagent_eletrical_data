package enums;

public enum EquipmentType {
	LOW_EFFICIENCY (1),
	ENERGY_STAR (2);
	
	private final int type;
	
	private EquipmentType(int type) {
		this.type = type;
	}
	
	public int getValue() {
		return this.type;
	}
}