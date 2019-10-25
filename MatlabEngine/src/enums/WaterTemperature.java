package enums;

public enum WaterTemperature {
	COLD (1),
	WARM (2),
	HOT (3);
	
	private final int status;
	
	private WaterTemperature(int status) {
		this.status = status;
	}
	
	public int getValue() {
		return this.status;
	}
}