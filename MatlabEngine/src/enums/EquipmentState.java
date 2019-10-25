package enums;

public enum EquipmentState {
	OFF (0),
	ON (1);
	
	private final int stateCode;
	
	private EquipmentState(int stateCode) {
		this.stateCode = stateCode;
	}
	
	public int getValue() {
		return this.stateCode;
	}
}