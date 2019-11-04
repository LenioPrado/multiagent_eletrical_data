package enums;

public enum StoveBurnerSize {
	SIX (0),
	SEVEN (1),
	EIGHT(2),
	NINE(3);
	
	private final int status;
	
	private StoveBurnerSize(int status) {
		this.status = status;
	}
	
	public int getValue() {
		return this.status;
	}
}